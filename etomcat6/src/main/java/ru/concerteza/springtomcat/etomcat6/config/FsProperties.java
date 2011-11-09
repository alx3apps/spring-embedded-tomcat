package ru.concerteza.springtomcat.etomcat6.config;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class FsProperties {
    private String confDir = "conf";
    private String webXmlPath = "web.xml";
    private String workDir = "work";
    private boolean useFsResources = false;
    private String docBaseDir = "static";

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

    public boolean isUseFsResources() {
        return useFsResources;
    }

    public void setUseFsResources(boolean useFsResources) {
        this.useFsResources = useFsResources;
    }

    public String getDocBaseDir() {
        return docBaseDir;
    }

    public void setDocBaseDir(String docBaseDir) {
        this.docBaseDir = docBaseDir;
    }
}
