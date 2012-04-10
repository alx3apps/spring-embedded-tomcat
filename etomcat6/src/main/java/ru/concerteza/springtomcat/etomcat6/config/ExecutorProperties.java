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
    private String namePrefix = "etomcat-";
    private int maxThreads = 200;
    private int minSpareThreads = 2;
    private int maxIdleTimeMs = 600000;
    private int threadPriority = Thread.NORM_PRIORITY;

    public String getName() {
        return name;
    }

    public ExecutorProperties setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isDaemon() {
        return daemon;
    }

    public ExecutorProperties setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public ExecutorProperties setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
        return this;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public ExecutorProperties setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
        return this;
    }

    public int getMinSpareThreads() {
        return minSpareThreads;
    }

    public ExecutorProperties setMinSpareThreads(int minSpareThreads) {
        this.minSpareThreads = minSpareThreads;
        return this;
    }

    public int getMaxIdleTimeMs() {
        return maxIdleTimeMs;
    }

    public ExecutorProperties setMaxIdleTimeMs(int maxIdleTimeMs) {
        this.maxIdleTimeMs = maxIdleTimeMs;
        return this;
    }

    public int getThreadPriority() {
        return threadPriority;
    }

    public ExecutorProperties setThreadPriority(int threadPriority) {
        this.threadPriority = threadPriority;
        return this;
    }
}
