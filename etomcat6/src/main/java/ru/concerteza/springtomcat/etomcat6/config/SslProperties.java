package ru.concerteza.springtomcat.etomcat6.config;


import javax.net.ssl.KeyManagerFactory;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class SslProperties {
    private String algorithm = KeyManagerFactory.getDefaultAlgorithm();
    private boolean clientAuth = false;
    private boolean sslEnabled = false;
    private String keystoreFile;
    private String keystorePass;
    private String keyAlias;
    private String keystoreType = "pkcs12";
    private String keystoreProvider = "SunJSSE";
    private String truststoreFile = "trustsrore.p12";
    private String truststorePass = "";
    private String truststoreType = "pkcs12";
    private String truststoreProvider = "SunJSSE";
    private String protocol = "TLS";
    private String ciphers = "TLS_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_DSS_WITH_AES_128_CBC_SHA,SSL_RSA_WITH_3DES_EDE_CBC_SHA,SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA,SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA";
    private int sessionCacheSize = 0;
    private int sessionTimeoutSecs = 86400;
    private String crlFile = "";

    public String getAlgorithm() {
        return algorithm;
    }

    public SslProperties setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public boolean isClientAuth() {
        return clientAuth;
    }

    public SslProperties setClientAuth(boolean clientAuth) {
        this.clientAuth = clientAuth;
        return this;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public SslProperties setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
        return this;
    }

    public String getKeystoreFile() {
        return keystoreFile;
    }

    public SslProperties setKeystoreFile(String keystoreFile) {
        this.keystoreFile = keystoreFile;
        return this;
    }

    public String getKeystorePass() {
        return keystorePass;
    }

    public SslProperties setKeystorePass(String keystorePass) {
        this.keystorePass = keystorePass;
        return this;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public SslProperties setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
        return this;
    }

    public String getKeystoreType() {
        return keystoreType;
    }

    public SslProperties setKeystoreType(String keystoreType) {
        this.keystoreType = keystoreType;
        return this;
    }

    public String getKeystoreProvider() {
        return keystoreProvider;
    }

    public SslProperties setKeystoreProvider(String keystoreProvider) {
        this.keystoreProvider = keystoreProvider;
        return this;
    }

    public String getTruststoreFile() {
        return truststoreFile;
    }

    public SslProperties setTruststoreFile(String truststoreFile) {
        this.truststoreFile = truststoreFile;
        return this;
    }

    public String getTruststorePass() {
        return truststorePass;
    }

    public SslProperties setTruststorePass(String truststorePass) {
        this.truststorePass = truststorePass;
        return this;
    }

    public String getTruststoreType() {
        return truststoreType;
    }

    public SslProperties setTruststoreType(String truststoreType) {
        this.truststoreType = truststoreType;
        return this;
    }

    public String getTruststoreProvider() {
        return truststoreProvider;
    }

    public SslProperties setTruststoreProvider(String truststoreProvider) {
        this.truststoreProvider = truststoreProvider;
        return this;
    }

    public String getProtocol() {
        return protocol;
    }

    public SslProperties setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public String getCiphers() {
        return ciphers;
    }

    public SslProperties setCiphers(String ciphers) {
        this.ciphers = ciphers;
        return this;
    }

    public int getSessionCacheSize() {
        return sessionCacheSize;
    }

    public SslProperties setSessionCacheSize(int sessionCacheSize) {
        this.sessionCacheSize = sessionCacheSize;
        return this;
    }

    public int getSessionTimeoutSecs() {
        return sessionTimeoutSecs;
    }

    public SslProperties setSessionTimeoutSecs(int sessionTimeoutSecs) {
        this.sessionTimeoutSecs = sessionTimeoutSecs;
        return this;
    }

    public String getCrlFile() {
        return crlFile;
    }

    public SslProperties setCrlFile(String crlFile) {
        this.crlFile = crlFile;
        return this;
    }
}
