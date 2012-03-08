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

    public void setConfDir(String confDir) {
        this.confDir = confDir;
    }

    public String getWebXmlPath() {
        return webXmlPath;
    }

    public void setWebXmlPath(String webXmlPath) {
        this.webXmlPath = webXmlPath;
    }

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }
}
