package ru.concerteza.springtomcat.etomcat6;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.jsslutils.extra.apachehttpclient.SslContextedSecureProtocolSocketFactory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.concerteza.springtomcat.etomcat6.components.TestFilter;
import ru.concerteza.springtomcat.etomcat6.components.TestListener;
import ru.concerteza.springtomcat.etomcat6.components.TestServlet;
import ru.concerteza.springtomcat.etomcat6.x509.SecuredService;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * User: alexey
 * Date: 8/28/11
 */

public class X509Test extends TestSupertype {
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
    }

    @Override
    protected String dirname() {
        return "x509dir";
    }

    protected void setupClientSsl() throws NoSuchProviderException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException {
        // truststore
        KeyStore trustStore = KeyStore.getInstance("JKS", "SUN");
        trustStore.load(TestSupertype.class.getResourceAsStream("/client-truststore.jks"), "amber%".toCharArray());
        String alg = KeyManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory fac = TrustManagerFactory.getInstance(alg);
        fac.init(trustStore);
        // keystore
        KeyStore keystore = KeyStore.getInstance("PKCS12", "SunJSSE");
        keystore.load(X509Test.class.getResourceAsStream("/etomcat_client.p12"), "etomcat".toCharArray());
        String keyAlg = KeyManagerFactory.getDefaultAlgorithm();
        KeyManagerFactory keyFac = KeyManagerFactory.getInstance(keyAlg);
        keyFac.init(keystore, "etomcat".toCharArray());
        // context
        SSLContext ctx = SSLContext.getInstance("TLS", "SunJSSE");
        ctx.init(keyFac.getKeyManagers(), fac.getTrustManagers(), new SecureRandom());
        SslContextedSecureProtocolSocketFactory secureProtocolSocketFactory = new SslContextedSecureProtocolSocketFactory(ctx);
        Protocol.registerProtocol("https", new Protocol("https", (ProtocolSocketFactory) secureProtocolSocketFactory, 8443));
    }
}
