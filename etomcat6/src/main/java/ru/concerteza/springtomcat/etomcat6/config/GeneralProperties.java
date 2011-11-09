package ru.concerteza.springtomcat.etomcat6.config;

/**
 * User: alexey
 * Date: 11/4/11
 */

public class GeneralProperties {

    private int port = 8080;
    private boolean sslEnabled = false;
    private String keystoreFile;
    private String keystorePass;
    private String keyAlias;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public String getKeystoreFile() {
        return keystoreFile;
    }

    public void setKeystoreFile(String keystoreFile) {
        this.keystoreFile = keystoreFile;
    }

    public String getKeystorePass() {
        return keystorePass;
    }

    public void setKeystorePass(String keystorePass) {
        this.keystorePass = keystorePass;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }
}
