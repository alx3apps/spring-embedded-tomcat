package ru.concerteza.springtomcat.etomcat6.config;

/**
 * User: alexey
 * Date: 11/4/11
 */

// http://tomcat.apache.org/tomcat-6.0-doc/config/context.html
public class ContextProperties {
    private boolean cookies = true;
    private boolean disableURLRewriting = true;
    private int cacheMaxSizeKb = 10240;
    private int cacheObjectMaxSizeKb = 512;
    private int cacheTTLSec = 5;
    private boolean cachingAllowed = false;
    private int unloadDelayMs = 2000;
    private int maxActiveSessions = -1;
    private int sessionTimeoutMinutes = 10;

    public boolean isCookies() {
        return cookies;
    }

    public void setCookies(boolean cookies) {
        this.cookies = cookies;
    }

    public boolean isDisableURLRewriting() {
        return disableURLRewriting;
    }

    public void setDisableURLRewriting(boolean disableURLRewriting) {
        this.disableURLRewriting = disableURLRewriting;
    }

    public int getCacheMaxSizeKb() {
        return cacheMaxSizeKb;
    }

    public void setCacheMaxSizeKb(int cacheMaxSizeKb) {
        this.cacheMaxSizeKb = cacheMaxSizeKb;
    }

    public int getCacheObjectMaxSizeKb() {
        return cacheObjectMaxSizeKb;
    }

    public void setCacheObjectMaxSizeKb(int cacheObjectMaxSizeKb) {
        this.cacheObjectMaxSizeKb = cacheObjectMaxSizeKb;
    }

    public int getCacheTTLSec() {
        return cacheTTLSec;
    }

    public void setCacheTTLSec(int cacheTTLSec) {
        this.cacheTTLSec = cacheTTLSec;
    }

    public boolean isCachingAllowed() {
        return cachingAllowed;
    }

    public void setCachingAllowed(boolean cachingAllowed) {
        this.cachingAllowed = cachingAllowed;
    }

    public int getUnloadDelayMs() {
        return unloadDelayMs;
    }

    public void setUnloadDelayMs(int unloadDelayMs) {
        this.unloadDelayMs = unloadDelayMs;
    }

    public int getMaxActiveSessions() {
        return maxActiveSessions;
    }

    public void setMaxActiveSessions(int maxActiveSessions) {
        this.maxActiveSessions = maxActiveSessions;
    }

    public int getSessionTimeoutMinutes() {
        return sessionTimeoutMinutes;
    }

    public void setSessionTimeoutMinutes(int sessionTimeoutMinutes) {
        this.sessionTimeoutMinutes = sessionTimeoutMinutes;
    }
}
