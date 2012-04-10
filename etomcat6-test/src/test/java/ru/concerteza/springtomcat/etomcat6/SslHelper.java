package ru.concerteza.springtomcat.etomcat6;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.jsslutils.extra.apachehttpclient.SslContextedSecureProtocolSocketFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * User: alexey
 * Date: 4/9/12
 */
public class SslHelper {
     public static void setupClientSsl() throws NoSuchProviderException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException {
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
