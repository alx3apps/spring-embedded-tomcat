//package ru.concerteza.springtomcat.etomcat6;
//
//import org.apache.commons.httpclient.protocol.Protocol;
//import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.UnhandledException;
//import org.jsslutils.extra.apachehttpclient.SslContextedSecureProtocolSocketFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import javax.net.ssl.*;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.*;
//
///**
// * User: alexey
// * Date: 11/4/11
// */
//
//@Service
//public class TestSupport {
//    @Resource
//    private ResourceLoader resourceLoader;
//    @Resource
//    private ApplicationContext context;
//
//    public void start(String appDirName) {
//
//    }
//
//    public void stop() {
//
//    }
//
//    public void setupSsl() {
//        try {
//            String alg = KeyManagerFactory.getDefaultAlgorithm();
//            TrustManagerFactory fac = TrustManagerFactory.getInstance(alg);
//            fac.init(loadTruststore("classpath:/client-truststore.jks"));
//            TrustManager[] tms = fac.getTrustManagers();
//            SSLContext ctx = SSLContext.getInstance("TLS", "SunJSSE");
//            ctx.init(new KeyManager[]{}, tms, new SecureRandom());
//            SslContextedSecureProtocolSocketFactory secureProtocolSocketFactory = new SslContextedSecureProtocolSocketFactory(ctx);
//            Protocol.registerProtocol("https", new Protocol("https", (ProtocolSocketFactory) secureProtocolSocketFactory, 8443));
//        } catch (GeneralSecurityException e) {
//            throw new UnhandledException(e);
//        }
//    }
//
//    private KeyStore loadTruststore(String path) throws NoSuchAlgorithmException, GeneralSecurityException {
//        InputStream containerStream = null;
//        try {
//            containerStream = resourceLoader.getResource(path).getInputStream();
//            KeyStore trustStore = KeyStore.getInstance("JKS", "SUN");
//            trustStore.load(containerStream, "amber%".toCharArray());
//            return trustStore;
//        } catch (IOException e) {
//            throw new UnhandledException(e);
//        } finally {
//            IOUtils.closeQuietly(containerStream);
//        }
//    }
//}
