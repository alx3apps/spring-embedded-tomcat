package ru.concerteza.springtomcat.etomcat8;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlStrategy;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import ru.concerteza.springtomcat.etomcat8.config.GeneralProperties;
import ru.concerteza.springtomcat.etomcat8.config.SslProperties;
import ru.concerteza.springtomcat.etomcat8.x509.AuthUserDetailService;
import ru.concerteza.springtomcat.etomcat8.x509.SecuredService;

import javax.inject.Inject;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static ru.concerteza.springtomcat.etomcat8.SslHelper.setupClientSsl;

/**
 * User: alexey
 * Date: 4/9/12
 */
public class X509AnnotationTest extends AnnotationTestSupertype {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testRunning() throws Exception {
        HttpClient client = new HttpClient();
        setupClientSsl();
        // test get
        HttpMethod get = new GetMethod("https://127.0.0.1:8443/etomcat_x509");
        client.executeMethod(get);
        byte[] responseBody = get.getResponseBody();
        String content = new String(responseBody, "UTF-8");
        Assert.assertEquals("Servlet get fail", SecuredService.GREETING, content);
        // test assess denied
        HttpMethod post = new PostMethod("https://127.0.0.1:8443/etomcat_x509");
        client.executeMethod(post);
        assertEquals("Method security fail get fail", 403, post.getStatusCode());
    }

    @Override
    protected String dirname() {
        return "x509annotationdir";
    }

    @Override
    protected Class<?> configClass() {
        return Config.class;
    }

    @Configuration
    // don't know how to enable it in java yet
    @ImportResource("classpath:/global-metod-security-enabler.xml")
    @ComponentScan(basePackages = "ru.concerteza.springtomcat.etomcat8.x509")
    public static class Config {
        @Value("${etomcat.port}") private int etomcatPort;
        @Value("${etomcat.ssl.keystoreFile}") private String keystoreFile;
        @Value("${etomcat.ssl.keystorePass}") private String keystorePass;
        @Value("${etomcat.ssl.keystoreProvider}") private String keystoreProvider;
        @Value("${etomcat.ssl.keyAlias}") private String keyAlias;
        @Value("${etomcat.ssl.truststoreFile}") private String truststoreFile;
        @Value("${etomcat.ssl.truststorePass}") private String truststorePass;
        @Value("${etomcat.ssl.truststoreType}") private String truststoreType;
        @Value("${etomcat.ssl.truststoreProvider}") private String truststoreProvider;
        @Inject
        AuthUserDetailService authUserDetailService;

        @Bean
        public static PropertySourcesPlaceholderConfigurer properties() {
            PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            Resource[] resources = new FileSystemResource[]{
                    new FileSystemResource("src/main/app-dirs/x509annotationdir/conf/etomcat.properties")};
            pspc.setLocations(resources);
            // pspc.setIgnoreUnresolvablePlaceholders(true);
            return pspc;
        }

        @Bean(destroyMethod = "stop")
        public EmbeddedTomcat etomcat() {
            return new EmbeddedTomcat()
                    .setGeneralProps(new GeneralProperties().setPort(etomcatPort))
                    .setSslProps(new SslProperties()
                            .setSslEnabled(true)
                            .setKeystoreFile(keystoreFile)
                            .setKeystorePass(keystorePass)
                            .setKeystoreProvider(keystoreProvider)
                            .setKeyAlias(keyAlias)
                            .setClientAuth(true)
                            .setTruststoreFile(truststoreFile)
                            .setTruststorePass(truststorePass)
                            .setTruststoreType(truststoreType)
                            .setTruststoreProvider(truststoreProvider));
        }

        @Bean
        public FilterChainProxy springSecurityFilterChain() throws Exception {
            // AuthenticationEntryPoint
            BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
            entryPoint.setRealmName("AppName Realm");
            // accessDecisionManager
            List<AccessDecisionVoter> voters = Arrays.<AccessDecisionVoter>asList(new RoleVoter(), new WebExpressionVoter());
            AccessDecisionManager accessDecisionManager = new AffirmativeBased(voters);
            // SecurityExpressionHandler
            SecurityExpressionHandler<FilterInvocation> securityExpressionHandler = new DefaultWebSecurityExpressionHandler();
            // AuthenticationUserDetailsService
            UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService = new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(authUserDetailService);
            authenticationUserDetailsService.afterPropertiesSet();
            // PreAuthenticatedAuthenticationProvider
            PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
            preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService);
            preAuthenticatedAuthenticationProvider.afterPropertiesSet();
            // AuthenticationManager
            List<AuthenticationProvider> providers = Arrays.<AuthenticationProvider>asList(preAuthenticatedAuthenticationProvider);
            AuthenticationManager authenticationManager = new ProviderManager(providers);
            // HttpSessionSecurityContextRepository
            HttpSessionSecurityContextRepository httpSessionSecurityContextRepository = new HttpSessionSecurityContextRepository();
            // SessionRegistry
            SessionRegistry sessionRegistry = new SessionRegistryImpl();
            // ConcurrentSessionControlStrategy
            ConcurrentSessionControlStrategy concurrentSessionControlStrategy = new ConcurrentSessionControlStrategy(sessionRegistry);

            // ConcurrentSessionFilter
            ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(sessionRegistry);
            concurrentSessionFilter.afterPropertiesSet();
            // SecurityContextPersistenceFilter
            SecurityContextPersistenceFilter securityContextPersistenceFilter = new SecurityContextPersistenceFilter(httpSessionSecurityContextRepository);
            // X509AuthenticationFilter
            X509AuthenticationFilter x509AuthenticationFilter = new X509AuthenticationFilter();
            x509AuthenticationFilter.setAuthenticationManager(authenticationManager);
            x509AuthenticationFilter.afterPropertiesSet();
            // RequestCacheAwareFilter
            RequestCacheAwareFilter requestCacheAwareFilter = new RequestCacheAwareFilter();
            // SecurityContextHolderAwareRequestFilter
            SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter = new SecurityContextHolderAwareRequestFilter();
            // SessionManagementFilter
            SessionManagementFilter sessionManagementFilter = new SessionManagementFilter(httpSessionSecurityContextRepository, concurrentSessionControlStrategy);
            // ExceptionTranslationFilter
            ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(entryPoint);
            exceptionTranslationFilter.setAccessDeniedHandler(new AccessDeniedHandlerImpl());
            exceptionTranslationFilter.afterPropertiesSet();
            // FilterSecurityInterceptor
            FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
            filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
            filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
            LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
            map.put(new AntPathRequestMatcher("/**"), Arrays.<ConfigAttribute>asList(new SecurityConfig("isAuthenticated()")));
            ExpressionBasedFilterInvocationSecurityMetadataSource ms = new ExpressionBasedFilterInvocationSecurityMetadataSource(map, securityExpressionHandler);
            filterSecurityInterceptor.setSecurityMetadataSource(ms);
            filterSecurityInterceptor.afterPropertiesSet();
            // SecurityFilterChain
            SecurityFilterChain chain = new DefaultSecurityFilterChain(new AntPathRequestMatcher("/**"),
                    concurrentSessionFilter,
                    securityContextPersistenceFilter,
                    x509AuthenticationFilter,
                    requestCacheAwareFilter,
                    securityContextHolderAwareRequestFilter,
                    sessionManagementFilter,
                    exceptionTranslationFilter,
                    filterSecurityInterceptor);
            return new FilterChainProxy(chain);
        }
    }

}
