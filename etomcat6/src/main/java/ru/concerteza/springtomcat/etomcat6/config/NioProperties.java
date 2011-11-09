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

    public void setUseSendfile(boolean useSendfile) {
        this.useSendfile = useSendfile;
    }

    public int getAcceptorThreadCount() {
        return acceptorThreadCount;
    }

    public void setAcceptorThreadCount(int acceptorThreadCount) {
        this.acceptorThreadCount = acceptorThreadCount;
    }

    public int getAcceptorThreadPriority() {
        return acceptorThreadPriority;
    }

    public void setAcceptorThreadPriority(int acceptorThreadPriority) {
        this.acceptorThreadPriority = acceptorThreadPriority;
    }

    public int getPollerThreadCount() {
        return pollerThreadCount;
    }

    public void setPollerThreadCount(int pollerThreadCount) {
        this.pollerThreadCount = pollerThreadCount;
    }

    public int getPollerThreadPriority() {
        return pollerThreadPriority;
    }

    public void setPollerThreadPriority(int pollerThreadPriority) {
        this.pollerThreadPriority = pollerThreadPriority;
    }

    public int getSelectorTimeoutMs() {
        return selectorTimeoutMs;
    }

    public void setSelectorTimeoutMs(int selectorTimeoutMs) {
        this.selectorTimeoutMs = selectorTimeoutMs;
    }

    public int getOomParachute() {
        return oomParachute;
    }

    public void setOomParachute(int oomParachute) {
        this.oomParachute = oomParachute;
    }

    public int getMaxSelectors() {
        return maxSelectors;
    }

    public void setMaxSelectors(int maxSelectors) {
        this.maxSelectors = maxSelectors;
    }

    public int getMaxSpareSelectors() {
        return maxSpareSelectors;
    }

    public void setMaxSpareSelectors(int maxSpareSelectors) {
        this.maxSpareSelectors = maxSpareSelectors;
    }
}
