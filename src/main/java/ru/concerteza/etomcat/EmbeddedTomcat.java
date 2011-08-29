package ru.concerteza.etomcat;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardThreadExecutor;
import org.apache.catalina.startup.Embedded;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.naming.resources.FileDirContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

import static java.io.File.separator;

/**
 * User: alexey Date: 6/21/11
 */

@Service
public class EmbeddedTomcat {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ApplicationContext springContext;

    // general
    @Value("${etomcat.port}")
    private int port;

    @Value("${etomcat.ssl.keystoreFile}")
    private String connectorSSLKeystoreFile;
    @Value("${etomcat.ssl.keystorePass}")
    private String connectorSSLKeystorePass;
    @Value("${etomcat.ssl.keyAlias}")
    private String connectorSSLKeyAlias;
    // FS
    @Value("${etomcat.confDir:conf}")
    private String confDir;
    @Value("${etomcat.webXmlFile:web.xml}")
    private String webXmlPath;
    @Value("${etomcat.workDir:work}")
    private String workDir;
    @Value("${etomcat.useFsResources:false}")
    private boolean useFsResources;
    @Value("${etomcat.staticDir:static}")
    private String docBaseDir;

    // Engine
    // http://tomcat.apache.org/tomcat-6.0-doc/config/engine.html
    @Value("${etomcat.engine.defaultHost:etomcat-engine}")
    private String engineName;

    // Host
    // http://tomcat.apache.org/tomcat-6.0-doc/config/host.html
    // todo
    @Value("${etomcat.host.appBase:}")
    private String hostAppBase;
    @Value("${etomcat.host.name:localhost}")
    private String hostName;
    @Value("${etomcat.host.errorReportValveClass:org.apache.catalina.valves.ErrorReportValve}")
    private String hostErrorReportValveClass;

    // Context
    // http://tomcat.apache.org/tomcat-6.0-doc/config/context.html
    @Value("${etomcat.context.cookies:false}")
    private boolean contextCookies;
    @Value("${etomcat.context.cacheMaxSize_kb:10240}")
    private int contextCacheMaxSize;
    @Value("${etomcat.context.cacheObjectMaxSize_kb:512}")
    private int contextCacheObjectMaxSize;
    @Value("${etomcat.context.cacheTTL_sec:5}")
    private int contextCacheTTL;
    @Value("${etomcat.context.cachingAllowed:false}")
    private boolean contextCachingAllowed;
    @Value("${etomcat.context.unloadDelay_ms:2000}")
    private int contextUnloadDelay;
    @Value("${etomcat.context.contextMaxActiveSessions:0}")
    private int contextMaxActiveSessions;

    // Connector
    // http://tomcat.apache.org/tomcat-6.0-doc/config/http.html
    @Value("${etomcat.connector.connectorEnableLookups:false}")
    private boolean connectorEnableLookups;
    @Value("${etomcat.connector.maxPostSize_bytes:2097152}")
    private int connectorMaxPostSize;
    @Value("${etomcat.connector.maxSavePostSize_bytes:4096}")
    private int connectorMaxSavePostSize;
//    @Value("${etomcat.connector.SSLEnabled:true}")
//    private boolean connectorSSLEnabled;
    @Value("${etomcat.connector.acceptCount:1000}")
    private int connectorAcceptCount;
    // todo testme
    @Value("${etomcat.connector.compressableMimeType:text/html,text/xml,text/plain}")
    private String connectorCompressableMimeType;
    @Value("${etomcat.connector.compression:off}")
    private String connectorCompression;
    @Value("${etomcat.connector.compressionMinSize_bytes:2048}")
    private int connectorCompressionMinSize;
    @Value("${etomcat.connector.noCompressionUserAgents:}")
    private String connectorNoCompressionUserAgents;
    //
    @Value("${etomcat.connector.connectionTimeout_millis:60000}")
    private int connectorConnectionTimeout;
    @Value("${etomcat.connector.keepAliveTimeout_millis:60000}")
    private int connectorKeepAliveTimeout;
    @Value("${etomcat.connector.disableUploadTimeout:false}")
    private boolean connectorDisableUploadTimeout;
    @Value("${etomcat.connector.maxHttpHeaderSize_bytes:8192}")
    private int connectorMaxHttpHeaderSize;
    @Value("${etomcat.connector.maxKeepAliveRequests:100}")
    private int connectorMaxKeepAliveRequests;
    @Value("${etomcat.connector.server:Embedded Servlet Container}")
    private String connectorServer;
    @Value("${etomcat.connector.socketBuffer_bytes:9000}")
    private int connectorSocketBuffer;
    @Value("${etomcat.connector.tcpNoDelay:true}")
    private boolean connectorTcpNoDelay;
    // NIO options
    @Value("${etomcat.connector.nio.useSendfile:true}")
    private boolean connectorUseSendfile;
    @Value("${etomcat.connector.nio.acceptorThreadCount:2}")
    private int connectorAcceptorThreadCount;
    // todo
    @Value("${etomcat.connector.nio.acceptorThreadPriority:5}")
    private int connectorAcceptorThreadPriority;
    @Value("${etomcat.connector.nio.pollerThreadCount:2}")
    private int connectorPollerThreadCount;
    @Value("${etomcat.connector.nio.pollerThreadPriority:5}")
    private int connectorPollerThreadPriority;
    @Value("${etomcat.connector.nio.selectorTimeout_millis:1000}")
    private int connectorSelectorTimeout;
    @Value("${etomcat.connector.nio.useComet:false}")
    private boolean connectorUseComet;
    @Value("${etomcat.connector.nio.processorCache:200}")
    private int connectorProcessorCache;
    @Value("${etomcat.connector.nio.selectorPool.oomParachute:1048576}")
    private int connectorOomParachute;
    // socket
    @Value("${etomcat.connector.nio.socket.directBuffer:false}")
    private boolean connectorSocketDirectBuffer;
    @Value("${etomcat.connector.nio.socket.rxBufSize_bytes:25188}")
    private int connectorSocketRxBufSize;
    @Value("${etomcat.connector.nio.socket.txBufSize_bytes:43800}")
    private int connectorSocketTxBufSize;
    @Value("${etomcat.connector.nio.socket.appReadBufSize_bytes:8192}")
    private int connectorSocketAppReadBufSize;
    @Value("${etomcat.connector.nio.socket.appWriteBufSize_bytes:8192}")
    private int connectorSocketAppWriteBufSize;
    // todo testme
    @Value("${etomcat.connector.nio.socket.bufferPool:500}")
    private int connectorSocketBufferPool;
    @Value("${etomcat.connector.nio.socket.bufferPoolSize_bytes:104857600}")
    private int connectorSocketBufferPoolSize;
    @Value("${etomcat.connector.nio.socket.processorCache:500}")
    private int connectorSocketProcessorCache;
    @Value("${etomcat.connector.nio.socket.keyCache:500}")
    private int connectorSocketKeyCache;
    @Value("${etomcat.connector.nio.socket.eventCache:500}")
    private int connectorSocketEventCache;
    // todo
    @Value("${etomcat.connector.nio.socket.tcpNoDelay:false}")
    private boolean connectorSocketTcpNoDelay;
    @Value("${etomcat.connector.nio.socket.soKeepAlive:false}")
    private boolean connectorSocketSoKeepAlive;
    @Value("${etomcat.connector.nio.socket.ooBInline:true}")
    private boolean connectorSocketOoBInline;
    @Value("${etomcat.connector.nio.socket.soReuseAddress:true}")
    private boolean connectorSocketSoReuseAddress;
    @Value("${etomcat.connector.nio.socket.soLingerOn:true}")
    private boolean connectorSocketSoLingerOn;
    @Value("${etomcat.connector.nio.socket.soLingerTime_sec:25}")
    private int connectorSocketSoLingerTime;
    @Value("${etomcat.connector.nio.socket.soTimeout_ms:5000}")
    private int connectorSocketSoTimeout;
    @Value("${etomcat.connector.nio.socket.soTrafficClass:28}")
    private int connectorSocketSoTrafficClass;
    @Value("${etomcat.connector.nio.socket.performanceConnectionTime:1}")
    private int connectorSocketPerformanceConnectionTime;
    @Value("${etomcat.connector.nio.socket.performanceLatency:0}")
    private int connectorSocketPerformanceLatency;
    @Value("${etomcat.connector.nio.socket.performanceBandwidth:1}")
    private int connectorSocketPerformanceBandwidth;
    @Value("${etomcat.connector.nio.socket.unlockTimeout_ms:250}")
    private int connectorSocketUnlockTimeout;
    // selector pool
    @Value("${etomcat.connector.nio.selectorPool.maxSelectors:200}")
    private int connectorSPMaxSelectors;
    @Value("${etomcat.connector.nio.selectorPool.maxSpareSelectors:-1}")
    private int connectorMPMaxSpareSelectors;
    // SSL
    @Value("${etomcat.connector.ssl.algorithm:SunX509}")
    private String connectorSSLAlgorithm;
    @Value("${etomcat.connector.ssl.clientAuth:false}")
    private boolean connectorSSLClientAuth;
    @Value("${etomcat.connector.ssl.keystoreType:pkcs12}")
    private String connectorSSLKeystoreType;
    @Value("${etomcat.connector.ssl.keystoreProvider:SunJSSE}")
    private String connectorSSLKeystoreProvider;
    @Value("${etomcat.connector.ssl.truststoreFile:truststore.p12}")
    private String connectorSSLTruststoreFile;
    @Value("${etomcat.connector.ssl.truststorePass:}")
    private String connectorSSLTruststorePass;
    @Value("${etomcat.connector.ssl.truststoreType:pkcs12}")
    private String connectorSSLTruststoreType;
    @Value("${etomcat.connector.ssl.truststoreProvider:SunJSSE}")
    private String connectorSSLTruststoreProvider;
    @Value("${etomcat.connector.ssl.sslProtocol:TLS}")
    private String connectorSSLProtocol;
    @Value("${etomcat.connector.ssl.ciphers:TLS_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_DSS_WITH_AES_128_CBC_SHA,SSL_RSA_WITH_3DES_EDE_CBC_SHA,SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA,SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA}")
    private String connectorSSLCiphers;
    @Value("${etomcat.connector.ssl.sessionCacheSize:0}")
    private int connectorSSLSessionCacheSize;
    @Value("${etomcat.connector.ssl.sessionTimeout:86400}")
    private int connectorSSLSessionTimeout;
    @Value("${etomcat.connector.ssl.crlFile:}")
    private String connectorSSLCrlFile;

    // Executor
    // http://tomcat.apache.org/tomcat-6.0-doc/config/executor.html
    @Value("${etomcat.executor.name:etomcat-executor}")
	private String executorName;
    @Value("${etomcat.executor.daemon:true}")
	private boolean executorDaemon;
    @Value("${etomcat.executor.namePrefix:etomcat}")
	private String executorNamePrefix;
    @Value("${etomcat.executor.maxThreads:200}")
	private int executorMaxThreads;
    @Value("${etomcat.executor.minSpareThreads:10}")
	private int executorMinSpareThreads;
    @Value("${etomcat.executor.maxIdleTime_ms:600000}")
	private int executorMaxIdleTime;
    @Value("${etomcat.executor.threadPriority:5}")
    private int executorThreadPriority;



	public Embedded start(File baseDir) throws Exception {
        logger.info("Starting Embedded Tomcat");
        // resolving paths
        Paths paths = new Paths(baseDir, confDir, workDir, docBaseDir, webXmlPath, connectorSSLKeystoreFile, connectorSSLTruststoreFile, connectorSSLCrlFile);
        // creating
		Embedded embedded = new Embedded();
		Engine engine = createEngine(paths);
        Host host = createHost(paths);
        Context context = createContext(paths);
        Connector connector = createConnector(paths);
        Executor executor = createExecutor();
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
        return embedded;
	}

    private Engine createEngine(Paths paths) {
        StandardEngine engine = new StandardEngine();
        engine.setName(engineName);
        engine.setBaseDir(paths.getBaseDir());
        return engine;
    }

    private Host createHost(Paths paths) {
        StandardHost host = new StandardHost();
        host.setAutoDeploy(false);
        host.setDeployOnStartup(false);
        host.setDeployXML(false);
        host.setUnpackWARs(false);

        host.setAppBase(hostAppBase);
        host.setName(hostName);
        host.setErrorReportValveClass(hostErrorReportValveClass);
        host.setWorkDir(paths.getWorkDir());
        return host;
    }

    private Context createContext(Paths paths) {
        StandardContext context = new StandardContext();
        context.setAltDDName(paths.getWebXmlFile());
        context.setPath("");
        context.setCrossContext(true);
        context.setPrivileged(true);
        context.setClearReferencesHttpClientKeepAliveThread(false);
        context.setProcessTlds(false);
        context.setUnpackWAR(false);
        context.setUseNaming(false);
        context.setConfigured(true);

        context.setCookies(contextCookies);
        context.setDocBase(paths.getDocBaseDir());
        context.setCacheMaxSize(contextCacheMaxSize);
        context.setCacheObjectMaxSize(contextCacheObjectMaxSize);
        context.setCacheTTL(contextCacheTTL);
        context.setCachingAllowed(contextCachingAllowed);
        context.setUnloadDelay(contextUnloadDelay);

        context.setLoader(new EmbeddedLoader());
        if(useFsResources) {
            context.setResources(new FileDirContext());
        } else {
            context.setResources(new EmbeddedDirContext());
        }
        context.setManager(createManager());

        context.addLifecycleListener(new EmbeddedContextConfig(paths.getWebXmlFile()));
        return context;
    }

    private Manager createManager() {
        EmbeddedManager manager = new EmbeddedManager();
        manager.setMaxActiveSessions(contextMaxActiveSessions);
        return manager;
    }

    private Connector createConnector(Paths paths) throws Exception {
        Connector con = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol proto = (Http11NioProtocol) con.getProtocolHandler();
        con.setEnableLookups(connectorEnableLookups);
        con.setMaxPostSize(connectorMaxPostSize);
        proto.setSSLEnabled(true);
        con.setScheme("https");
        con.setSecure(true);
        con.setURIEncoding("UTF-8");

        con.setProperty("acceptCount", Integer.toString(connectorAcceptCount));
        proto.setCompressableMimeType(connectorCompressableMimeType);
        proto.setCompression(connectorCompression);
        proto.setCompressionMinSize(connectorCompressionMinSize);
        if(!connectorNoCompressionUserAgents.isEmpty()) proto.setNoCompressionUserAgents(connectorNoCompressionUserAgents);
        con.setProperty("connectionTimeout", Integer.toString(connectorConnectionTimeout));
        con.setProperty("keepAliveTimeout", Integer.toString(connectorKeepAliveTimeout));
        proto.setDisableUploadTimeout(connectorDisableUploadTimeout);
        proto.setMaxHttpHeaderSize(connectorMaxHttpHeaderSize);
        proto.setMaxKeepAliveRequests(connectorMaxKeepAliveRequests);
        con.setPort(port);
        proto.setServer(connectorServer);
        proto.setSocketBuffer(connectorSocketBuffer);
        proto.setTcpNoDelay(connectorTcpNoDelay);


        proto.setUseSendfile(connectorUseSendfile);
        // todo check
        con.setProperty("acceptorThreadCount", Integer.toString(connectorAcceptorThreadCount));
        proto.setAcceptorThreadPriority(connectorAcceptorThreadPriority);
        proto.setPollerThreadCount(connectorPollerThreadCount);
        proto.setPollerThreadPriority(connectorPollerThreadPriority);
        proto.setSelectorTimeout(connectorSelectorTimeout);
        // todo check
        con.setProperty("useComet", Boolean.toString(connectorUseComet));
        proto.setProcessorCache(connectorProcessorCache);
        proto.setUseExecutor(true);
        con.setProperty("socket.directBuffer", Boolean.toString(connectorSocketDirectBuffer));
        con.setProperty("socket.rxBufSize", Integer.toString(connectorSocketRxBufSize));
        con.setProperty("socket.txBufSize", Integer.toString(connectorSocketTxBufSize));
        con.setProperty("socket.appReadBufSize", Integer.toString(connectorSocketAppReadBufSize));
        con.setProperty("socket.appWriteBufSize", Integer.toString(connectorSocketAppWriteBufSize));
        con.setProperty("socket.bufferPool", Integer.toString(connectorSocketBufferPool));
        con.setProperty("socket.bufferPoolSize", Integer.toString(connectorSocketBufferPoolSize));
        con.setProperty("socket.processorCache", Integer.toString(connectorSocketProcessorCache));
        con.setProperty("socket.keyCache", Integer.toString(connectorSocketKeyCache));
        con.setProperty("socket.eventCache", Integer.toString(connectorSocketEventCache));
        con.setProperty("socket.tcpNoDelay", Boolean.toString(connectorSocketTcpNoDelay));
        con.setProperty("socket.soKeepAlive", Boolean.toString(connectorSocketSoKeepAlive));
        con.setProperty("socket.ooBInline", Boolean.toString(connectorSocketOoBInline));
        con.setProperty("socket.soReuseAddress", Boolean.toString(connectorSocketSoReuseAddress));
        con.setProperty("socket.soLingerOn", Boolean.toString(connectorSocketSoLingerOn));
        con.setProperty("socket.soLingerTime", Integer.toString(connectorSocketSoLingerTime));
        con.setProperty("socket.soTimeout", Integer.toString(connectorSocketSoTimeout));
        con.setProperty("socket.soTrafficClass", Integer.toString(connectorSocketSoTrafficClass));
        con.setProperty("socket.performanceConnectionTime", Integer.toString(connectorSocketPerformanceConnectionTime));
        con.setProperty("socket.performanceLatency", Integer.toString(connectorSocketPerformanceLatency));
        con.setProperty("socket.performanceBandwidth", Integer.toString(connectorSocketPerformanceBandwidth));
        con.setProperty("socket.unlockTimeout", Integer.toString(connectorSocketUnlockTimeout));
        con.setProperty("selectorPool.maxSelectors", Integer.toString(connectorSPMaxSelectors));
        con.setProperty("selectorPool.maxSpareSelectors", Integer.toString(connectorMPMaxSpareSelectors));
        // SSL
        proto.setKeystoreFile(paths.getKeystoreFile());
        proto.setKeystorePass(connectorSSLKeystorePass);
        proto.setKeystoreType(connectorSSLKeystoreType);
        proto.setProperty("keystoreProvider", connectorSSLKeystoreProvider);
        proto.setKeyAlias(connectorSSLKeyAlias);
//        proto.setKeypass(connectorSSLKeyPass);
        proto.setAlgorithm(connectorSSLAlgorithm);
        if(connectorSSLClientAuth) {
            proto.setClientAuth("true");
            proto.setTruststoreFile(paths.getTruststoreFile());
            proto.setTruststorePass(connectorSSLTruststorePass);
            proto.setTruststoreType(connectorSSLTruststoreType);
            con.setProperty("truststoreProvider", connectorSSLTruststoreProvider);
        }
        proto.setSslProtocol(connectorSSLProtocol);
        proto.setCiphers(connectorSSLCiphers);
        con.setProperty("sessionCacheSize", Integer.toString(connectorSSLSessionCacheSize));
        con.setProperty("sessionTimeout", Integer.toString(connectorSSLSessionTimeout));
        if(!connectorSSLCrlFile.isEmpty()) con.setProperty("crlFile", paths.getCrlFile());

        return con;
    }

    private Executor createExecutor() {
        StandardThreadExecutor executor = new StandardThreadExecutor();
        executor.setThreadPriority(executorThreadPriority);
		executor.setName(executorName);
		executor.setDaemon(executorDaemon);
		executor.setNamePrefix(executorNamePrefix);
		executor.setMaxThreads(executorMaxThreads);
		executor.setMinSpareThreads(executorMinSpareThreads);
		executor.setMaxIdleTime(executorMaxIdleTime);
		return executor;
    }

    class Paths {
        private final String baseDir;
        private final String confDir;
        private final String workDir;
        private final String docBaseDir;
        private final String webXmlFile;
        private final String keystoreFile;
        private final String truststoreFile;
        private final String crlFile;

        private Paths(File baseDir, String confDir, String workDir, String docBaseDir, String webXmlFile, String keystoreFile, String truststoreFile, String crlFile) {
            this.baseDir = baseDir.getAbsolutePath();
            this.confDir = this.baseDir + separator + confDir;
            this.workDir = this.baseDir + separator + workDir;
            this.docBaseDir = this.baseDir + separator + docBaseDir;
            this.webXmlFile = this.confDir + separator + webXmlFile;
            this.keystoreFile = this.confDir + separator + keystoreFile;
            this.truststoreFile = this.confDir + separator + truststoreFile;
            this.crlFile = this.confDir + separator + crlFile;
        }

        public String getBaseDir() {
            return baseDir;
        }

        public String getConfDir() {
            return confDir;
        }

        public String getWorkDir() {
            return workDir;
        }

        public String getDocBaseDir() {
            return docBaseDir;
        }

        public String getWebXmlFile() {
            return webXmlFile;
        }

        public String getKeystoreFile() {
            return keystoreFile;
        }

        public String getTruststoreFile() {
            return truststoreFile;
        }

        public String getCrlFile() {
            return crlFile;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("Paths");
            sb.append("{baseDir='").append(baseDir).append('\'');
            sb.append(", confDir='").append(confDir).append('\'');
            sb.append(", workDir='").append(workDir).append('\'');
            sb.append(", docBaseDir='").append(docBaseDir).append('\'');
            sb.append(", webXmlFile='").append(webXmlFile).append('\'');
            sb.append(", keystoreFile='").append(keystoreFile).append('\'');
            sb.append(", truststoreFile='").append(truststoreFile).append('\'');
            sb.append(", crlFile='").append(crlFile).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
