package ru.concerteza.springtomcat.etomcat6.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * User: alexey
 * Date: 11/4/11
 */

// http://tomcat.apache.org/tomcat-6.0-doc/config/http.html
public class NioProperties {
    private boolean useSendfile = true;
    private int acceptorThreadCount = 2;
    private int acceptorThreadPriority = Thread.NORM_PRIORITY;
    private int pollerThreadCount = 2;
    private int pollerThreadPriority = Thread.NORM_PRIORITY;
    private int selectorTimeoutMs = 1000;
    private int oomParachute = 1048576;
    private int maxSelectors = 200;
    private int maxSpareSelectors = -1;

    public boolean isUseSendfile() {
        return useSendfile;
    }

    public NioProperties setUseSendfile(boolean useSendfile) {
        this.useSendfile = useSendfile;
        return this;
    }

    public int getAcceptorThreadCount() {
        return acceptorThreadCount;
    }

    public NioProperties setAcceptorThreadCount(int acceptorThreadCount) {
        this.acceptorThreadCount = acceptorThreadCount;
        return this;
    }

    public int getAcceptorThreadPriority() {
        return acceptorThreadPriority;
    }

    public NioProperties setAcceptorThreadPriority(int acceptorThreadPriority) {
        this.acceptorThreadPriority = acceptorThreadPriority;
        return this;
    }

    public int getPollerThreadCount() {
        return pollerThreadCount;
    }

    public NioProperties setPollerThreadCount(int pollerThreadCount) {
        this.pollerThreadCount = pollerThreadCount;
        return this;
    }

    public int getPollerThreadPriority() {
        return pollerThreadPriority;
    }

    public NioProperties setPollerThreadPriority(int pollerThreadPriority) {
        this.pollerThreadPriority = pollerThreadPriority;
        return this;
    }

    public int getSelectorTimeoutMs() {
        return selectorTimeoutMs;
    }

    public NioProperties setSelectorTimeoutMs(int selectorTimeoutMs) {
        this.selectorTimeoutMs = selectorTimeoutMs;
        return this;
    }

    public int getOomParachute() {
        return oomParachute;
    }

    public NioProperties setOomParachute(int oomParachute) {
        this.oomParachute = oomParachute;
        return this;
    }

    public int getMaxSelectors() {
        return maxSelectors;
    }

    public NioProperties setMaxSelectors(int maxSelectors) {
        this.maxSelectors = maxSelectors;
        return this;
    }

    public int getMaxSpareSelectors() {
        return maxSpareSelectors;
    }

    public NioProperties setMaxSpareSelectors(int maxSpareSelectors) {
        this.maxSpareSelectors = maxSpareSelectors;
        return this;
    }
}
