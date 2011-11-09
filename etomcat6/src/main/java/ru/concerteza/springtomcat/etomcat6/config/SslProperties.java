package ru.concerteza.springtomcat.etomcat6.config;


import javax.net.ssl.KeyManagerFactory;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class SslProperties {
    private String algorithm = KeyManagerFactory.getDefaultAlgorithm();
    private boolean clientAuth = false;
    private String keystoreType = "pkcs12";
    private String keystoreProvider = "SunJSSE";
    private String truststoreFile = "trustsrore.p12";
    private String truststorePass = "";
    private String truststoreType = "pkcs12";
    private String truststoreProvider = "SunJSSE";
    private String protocol = "TLS";
    private String ciphers = "TLS_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_DSS_WITH_AES_128_CBC_SHA,SSL_RSA_WITH_3DES_EDE_CBC_SHA,SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA,SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA";
    private int sessionCacheSize = 0;
    private int sessionTimeout = 86400;
    private String crlFile = "";

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public boolean isClientAuth() {
        return clientAuth;
    }

    public void setClientAuth(boolean clientAuth) {
        this.clientAuth = clientAuth;
    }

    public String getKeystoreType() {
        return keystoreType;
    }

    public void setKeystoreType(String keystoreType) {
        this.keystoreType = keystoreType;
    }

    public String getKeystoreProvider() {
        return keystoreProvider;
    }

    public void setKeystoreProvider(String keystoreProvider) {
        this.keystoreProvider = keystoreProvider;
    }

    public String getTruststoreFile() {
        return truststoreFile;
    }

    public void setTruststoreFile(String truststoreFile) {
        this.truststoreFile = truststoreFile;
    }

    public String getTruststorePass() {
        return truststorePass;
    }

    public void setTruststorePass(String truststorePass) {
        this.truststorePass = truststorePass;
    }

    public String getTruststoreType() {
        return truststoreType;
    }

    public void setTruststoreType(String truststoreType) {
        this.truststoreType = truststoreType;
    }

    public String getTruststoreProvider() {
        return truststoreProvider;
    }

    public void setTruststoreProvider(String truststoreProvider) {
        this.truststoreProvider = truststoreProvider;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getCiphers() {
        return ciphers;
    }

    public void setCiphers(String ciphers) {
        this.ciphers = ciphers;
    }

    public int getSessionCacheSize() {
        return sessionCacheSize;
    }

    public void setSessionCacheSize(int sessionCacheSize) {
        this.sessionCacheSize = sessionCacheSize;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getCrlFile() {
        return crlFile;
    }

    public void setCrlFile(String crlFile) {
        this.crlFile = crlFile;
    }
}
