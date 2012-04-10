package ru.concerteza.springtomcat.etomcat6.config;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class FsProperties {
    private String confDir = "conf";
    private String webXmlPath = "web.xml";
    private String workDir = "work";

    public String getConfDir() {
        return confDir;
    }

    public FsProperties setConfDir(String confDir) {
        this.confDir = confDir;
        return this;
    }

    public String getWebXmlPath() {
        return webXmlPath;
    }

    public FsProperties setWebXmlPath(String webXmlPath) {
        this.webXmlPath = webXmlPath;
        return this;
    }

    public String getWorkDir() {
        return workDir;
    }

    public FsProperties setWorkDir(String workDir) {
        this.workDir = workDir;
        return this;
    }
}
