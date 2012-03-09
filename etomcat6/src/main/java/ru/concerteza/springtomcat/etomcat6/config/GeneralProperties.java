package ru.concerteza.springtomcat.etomcat6.config;

/**
 * User: alexey
 * Date: 11/4/11
 */

public class GeneralProperties {

    private int port = 8080;
    private String docBaseDir = "";
    private String contextPath = "";

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDocBaseDir() {
        return docBaseDir;
    }

    public void setDocBaseDir(String docBaseDir) {
        this.docBaseDir = docBaseDir;
    }

    public boolean isUseFsResources() {
        return null != docBaseDir && docBaseDir.length() > 0;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
