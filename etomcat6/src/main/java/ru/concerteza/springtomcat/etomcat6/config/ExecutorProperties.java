package ru.concerteza.springtomcat.etomcat6.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * User: alexey
 * Date: 11/4/11
 */

// http://tomcat.apache.org/tomcat-6.0-doc/config/executor.html
public class ExecutorProperties {
    private String name = "etomcat-executor";
    private boolean daemon = true;
    private String namePrefix = "etomcat";
    private int maxThreads = 200;
    private int minSpareThreads = 2;
    private int maxIdleTimeMs = 600000;
    private int threadPriority = Thread.NORM_PRIORITY;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDaemon() {
        return daemon;
    }

    public void setDaemon(boolean daemon) {
        this.daemon = daemon;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getMinSpareThreads() {
        return minSpareThreads;
    }

    public void setMinSpareThreads(int minSpareThreads) {
        this.minSpareThreads = minSpareThreads;
    }

    public int getMaxIdleTimeMs() {
        return maxIdleTimeMs;
    }

    public void setMaxIdleTimeMs(int maxIdleTimeMs) {
        this.maxIdleTimeMs = maxIdleTimeMs;
    }

    public int getThreadPriority() {
        return threadPriority;
    }

    public void setThreadPriority(int threadPriority) {
        this.threadPriority = threadPriority;
    }
}
