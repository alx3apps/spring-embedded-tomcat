package ru.concerteza.etomcat;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.jsslutils.extra.apachehttpclient.SslContextedSecureProtocolSocketFactory;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

import static java.io.File.separator;

/**
 * User: alexey
 * Date: 8/28/11
 */

public class IntegrationalTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationContext ctx;

    @Before
    public void setUp() throws Exception {
        ctx = new EmbeddedSpringContext("classpath:/etomcat-test-ctx.xml");
        File baseDir = new File("src" + separator + "test" + separator + "appdir");
        ctx.getBean(EmbeddedTomcat.class).start(baseDir);
    }

    @After
    public void tearDown() {
        ctx.getBean(EmbeddedTomcat.class).stop();
    }

    @Test
    public void testRunning() throws Exception {
        HttpClient client = new HttpClient();
        setupSsl();
        HttpMethod method = new GetMethod("https://127.0.0.1:8443/etomcat_test");
//        Thread.sleep(10000000);
        client.executeMethod(method);
        byte[] responseBody = method.getResponseBody();
        String content = new String(responseBody, "UTF-8");
        Assert.assertEquals("Servlet fail", TestServlet.CONTENT, content);
        Assert.assertTrue("Listener fail", TestListener.initialized);
        Assert.assertTrue("Filter init fail", TestFilter.initialized);
        Assert.assertTrue("Filter fail", TestFilter.filtered);
    }

    private void setupSsl() throws NoSuchProviderException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, KeyManagementException {
        KeyStore trustStore = KeyStore.getInstance("JKS", "SUN");
        trustStore.load(ctx.getResource("classpath:/client-truststore.jks").getInputStream(), "amber%".toCharArray());
        String alg = KeyManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory fac = TrustManagerFactory.getInstance(alg);
        fac.init(trustStore);
        TrustManager[] tms = fac.getTrustManagers();
        SSLContext ctx = SSLContext.getInstance("TLS", "SunJSSE");
        ctx.init(new KeyManager[] {}, tms, new SecureRandom());
        SslContextedSecureProtocolSocketFactory secureProtocolSocketFactory = new SslContextedSecureProtocolSocketFactory(ctx);
        Protocol.registerProtocol("https", new Protocol("https", (ProtocolSocketFactory) secureProtocolSocketFactory, 8443));
    }

}
