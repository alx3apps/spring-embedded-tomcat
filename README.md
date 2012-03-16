Apache Tomcat 6 embedded into Spring Context
============================================

Apache Tomcat 6 embedded into Spring context as ordinary Spring bean using [Embedded API](http://tomcat.apache.org/tomcat-6.0-doc/api/org/apache/catalina/startup/Embedded.html).

Problem
-------

Tomcat is a great and extremly widespread piece of software. It's rock stable, good documented etc.
But it is designed as a __container__ to run __multiple__ web applications. But during my work in most
projects I participated, container was used to run __one__ __and__ __only__ __one__ application.
Some of the apps were internet ones, most we intranet (in fact for intranet usually multiple hardware servers
were used to run single application).

Maybe my experience is specific, but I'm sure I'm not alone in Java world with such experience.

So, __the__ __problem__ is: use web container to run single web application without overhead introduced by
multi-application design.

Ultimate goal
-------------

We are using Spring Framework heavily and we are happy with it. And we are used it with __single__ application context.
Different modules may have their own context parts (through annotations or XML), but in runtime all this snippets are
included into one root application context. May be this approach is arguable, but it definitely gives a good level of
certainty: if context started successfully, then app is really started and ready for users.

We are using JBoss Netty NIO server in one of our applications. It is configured as ordinary spring bean: all configuration
parameters are injected from context. Context successful start means Netty started and ready to accept socket connections.

So, __the__ __goal__ is: use HTTP servlet container in embedded mode (the same way as we using Netty) - as a spring bean running in the
same app context as web app itself and configurable from this context.

Why not Jetty?
--------------

_Note: I've used Jetty very little and not an expert in its configuration, next statements about Jetty might be outdated or simply wrong._

Jetty was the first thing we come to researching this problem. I played with it for some time and dropped for next reasons:

 - worse documentation comparing against tomcat
 - already existed and working non-trivial (X.509 client certs, NIO, etc.) tomcat-specific configuration for our app
 - in all examples I found about embedding into spring, Jetty always were run from __it's__ __own__ __separate__
 app context and application were run into it's own app context. And these contexts have __separate__ __config__ __properties__ sets
 (I know that config properties may be propagated from one context to another, but don't like such crutch).
 It's explainable approach, but it was not one we needed for our application. The goal is "container embedded into application"
 and Jetty doesn't give it out of the box.

Why not single unpackaged WAR application?
------------------------------------------

So we returned to Tomcat. In that moment we haven't some spare weeks to researching it's embedding. So we choose the
simple and reliable way - use full tomcat without embedding but with custom distribution package build. Our app is the only one,
so we don't need to pack it as WAR, redeploy hotly etc.

In result application comes to servers as full preconfigured tomcat with only one app already unpackaged.
If we want to start application - we start tomcat. Want to re-run - stop and run tomcat.
Need to undeploy and deploy new version - stop and delete full tomcat directory and copy new one on it's place. So we have no
permgen growth, no running threads left after undeploy, no forgotten thread-locals etc. It is simple and it works fine.
Also unneeded tomcat libraries were removed, config stripped, etc., e.g. our server.xml looks like:

    <?xml version='1.0' encoding='utf-8'?>
    <Server port="8005" shutdown="SHUTDOWN">
        <Service name="Catalina">
            <Executor name="app-container-executor"
                      daemon="true"
                      namePrefix="container"
                      maxThreads="500"
                      minSpareThreads="25"
                      maxIdleTime="600000"/>
            <Connector protocol="org.apache.coyote.http11.Http11NioProtocol"
                       other connector params.../>
            <Engine name="Catalina" defaultHost="localhost">
                <Host name="localhost"
                      appBase="webapps"
                      unpackWARs="false"
                      autoDeploy="false"
                      deployOnStartup="false"
                      xmlValidation="false"
                      xmlNamespaceAware="false">
                    <Context docBase="app" path="/ourapp">
                        <WatchedResource>WEB-INF/web.xml</WatchedResource>
                        <Manager pathname=""/>
                    </Context>
                </Host>
            </Engine>
        </Service>
    </Server>

This approach works fine, but goal is not there, tomcat has separate non-spring configurations, after application fail
at startup tomcat still starts etc.

How does it work?
-----------------

Actual work was not complex, [Embedded API](http://tomcat.apache.org/tomcat-6.0-doc/api/org/apache/catalina/startup/Embedded.html)
may be used in ten lines of code. But it was tedious, because tomcat is highly configurable, and I wanted to expose most of
it's options to be injected from spring.
Embedded startup proccess is quite simple:

      embedded = new Embedded();
      executor = createExecutor();
      StandardEngine engine = createEngine(paths);
      Host host = createHost(paths);
      StandardContext context = createContext(paths);
      Connector connector = createConnector(paths);
      // tomcat binding
      embedded.addEngine(engine);
      engine.setDefaultHost(host.getName());
      engine.addChild(host);
      host.addChild(context);
      Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
      proto.setExecutor(executor);
      embedded.addConnector(connector);
      // spring binding
      EmbeddedSpringContext embeddedSpringContext = (EmbeddedSpringContext) springContext;
      embeddedSpringContext.bind(context.getServletContext());
      // starting
      executor.start();
      embedded.start();

But it has one thin point: we want to run out application as CLI application using `ClassPathXmlApplicationContext`.
But web libraries that integrates with spring (Wicket, CXF etc.) want to have `WebApplicationContext`, and want to have it in it's standard
place in `ServletContext` under `WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE`.

So we must use spring context that is `ClassPath` and `Web` one simultaniously and can bind itself to ServletContext
(and vice versa):

    public class EmbeddedSpringContext extends AbstractXmlApplicationContext implements WebApplicationContext {
        private ServletContext servletContext;
        public EmbeddedSpringContext(String configLocation) throws BeansException {
            super(null);
            setConfigLocations(new String[]{configLocation});
            refresh();
        }
        public ServletContext getServletContext() {
            return servletContext;
        }
        public void bind(ServletContext servletContext) {
            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this);
            this.servletContext = servletContext;
        }
    }

It makes all the magic. Besides many config classes some intresting are:

 - `EmbeddedContextConfig` - to use standard `web.xml` parsing
 - `EmbeddedDirContext` and `EmbeddedLoader` - to be able to run without accessing any content on HD,
 added for spring-http-remoting based non-web applications
 - `EmbeddedManager` - to be able to add some fancy session management logic if needed in the future

Usage examples
--------------

Tomcat with default configuration may be embedded into spring context with this line:

    <bean id="etomcat" class="ru.concerteza.springtomcat.etomcat6.EmbeddedTomcat" destroy-method="stop" />

In this example it runs withous `docBaseDir` - so it cannot serve static content at all.
To be able to serve static content you should provide `docBaseDir` parameter:

    <bean id="etomcat" class="ru.concerteza.springtomcat.etomcat6.EmbeddedTomcat" destroy-method="stop">
        <property name="generalProps">
            <bean class="ru.concerteza.springtomcat.etomcat6.config.GeneralProperties">
                <property name="docBaseDir" value="${etomcat.docBaseDir}"/>
            </bean>
        </property>
    </bean>

Tomcat should be started from your code providing `CATALINA_HOME` directory:

    ApplicationContext ctx = loadContext(cline, CONTEXT_PATH);
    ctx.getBean(EmbeddedTomcat.class).start(APP_ROOT);

It may be stopped using `stop` method.

More complex usage example:

    <bean id="etomcat" class="ru.concerteza.springtomcat.etomcat6.EmbeddedTomcat" destroy-method="stop">
        <property name="generalProps">
            <bean class="ru.concerteza.springtomcat.etomcat6.config.GeneralProperties">
                <property name="port" value="${app.etomcat.port:8443}"/>
                <property name="docBaseDir" value="webapp"/>
                <property name="contextPath" value="/app"/>
            </bean>
        </property>
        <property name="sslProps">
            <bean class="ru.concerteza.springtomcat.etomcat6.config.SslProperties">
                <property name="sslEnabled" value="true"/>
                <property name="keystoreFile" value="${app.etomcat.ssl.keystoreFile:cert/keystore.jks}"/>
                <property name="keystorePass" value="${app.etomcat.ssl.keystorePass:changeme}"/>
                <property name="keyAlias" value="${app.etomcat.ssl.keyAlias:app_server}"/>
                <property name="clientAuth" value="true"/>
                <property name="keystoreType" value="${app.etomcat.ssl.keystoreType:JKS}"/>
                <property name="truststoreFile" value="${app.etomcat.ssl.truststoreFile:cert/truststore.jks}" />
                <property name="truststorePass" value="${app.etomcat.ssl.truststorePass:changeme}" />
                <property name="truststoreType" value="${app.etomcat.ssl.truststoreType:JKS}" />
                <property name="sessionTimeoutSecs" value="60" />
            </bean> am
        </property>
        <property name="executorProps">
            <bean class="ru.concerteza.springtomcat.etomcat6.config.ExecutorProperties">
                <property name="maxThreads" value="${app.etomcat.executor.maxThreads:500}"/>
                <property name="minSpareThreads" value="${app.etomcat.executor.minSpareThreads:25}"/>
                <property name="maxIdleTimeMs" value="${app.etomcat.executor.maxIdleTimeMs:600000}"/>
            </bean>
        </property>
        <property name="connectorProps">
            <bean class="ru.concerteza.springtomcat.etomcat6.config.ConnectorProperties">
                <property name="maxKeepAliveRequests" value="${app.etomcat.connector.maxKeepAliveRequests:500}"/>
                <property name="disableUploadTimeout" value="${app.etomcat.connector.disableUploadTimeout:true}"/>
                <property name="compression" value="${app.etomcat.connector.compression:true}"/>
                <property name="server" value="${app.etomcat.connector.server:App HTTP Server/2.8}"/>
            </bean>
        </property>
        <property name="contextProps">
            <bean class="ru.concerteza.springtomcat.etomcat6.config.ContextProperties">
                <property name="sessionTimeoutMinutes" value="${app.etomcat.context.sessionTimeoutMinutes:240}"/>
            </bean>
        </property>
    </bean>

In examples and tests many important properties (like web.xml naming and location) are omitted to use default value.
You may find these default values and other options in properties classes in package `ru.concerteza.springtomcat.etomcat6.config`
Most of properties are named the same as tomcat's properties (some postfixes added like 'fooSecs' and 'fooBytes' instead of 'foo').

Included examples and tests
---------------------------

Usage examples are included in `etomcat6-test` project, there are JUnit tests for all of them.
Application directories (tomcat's `CATALINA_HOME`s) are in the `etomcat6-test/src/main/app-dirs`:

####nosslappdir
`NoSslTest` - the most simple usage example

####appdir
`AppTest` - HTTPs usage example

####webappdir
`WebAppTest` - example of serving static context with tomcat's `DefaultServlet`

####x509dir
`X509Test` - example of old-styled plain bean XML Spring Security configuration for client certificate auth mode.

How to build
------------

Running

    mvn clean install

from project root will build all modules and run all tests. Note - tomcat started, accessed by HttpClient and stopped
in every test, you must have port `8080` and `8443` free.

Caveats
-------

Some caveats:
 - all path parameters are resolved relatively to `CATALINA_HOME` directory
 - most, but not all config supported from web.xml, e.g. `sessionTimeout` must be set as
 `contextProps.sessionTimeoutMinutes` not in web.xml
 - no server.xml config file, not all it's features supported, e.g. you cannot configure tomcat Valves without changing code
 - different default values for some properties, see classes in package `ru.concerteza.springtomcat.etomcat6.config`
 - only `Http11NioProtocol` is supported

If you are really want to embed tomcat into spring I recommend to fork this project and customize it for your application.

License  information
--------------------

You can use code from this project under terms of [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
