package ru.concerteza.springtomcat.etomcat8.config;

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
    private String postCharacterEncoding = "UTF-8";

    public boolean isCookies() {
        return cookies;
    }

    public ContextProperties setCookies(boolean cookies) {
        this.cookies = cookies;
        return this;
    }

    public boolean isDisableURLRewriting() {
        return disableURLRewriting;
    }

    public ContextProperties setDisableURLRewriting(boolean disableURLRewriting) {
        this.disableURLRewriting = disableURLRewriting;
        return this;
    }

    public int getCacheMaxSizeKb() {
        return cacheMaxSizeKb;
    }

    public ContextProperties setCacheMaxSizeKb(int cacheMaxSizeKb) {
        this.cacheMaxSizeKb = cacheMaxSizeKb;
        return this;
    }

    public int getCacheObjectMaxSizeKb() {
        return cacheObjectMaxSizeKb;
    }

    public ContextProperties setCacheObjectMaxSizeKb(int cacheObjectMaxSizeKb) {
        this.cacheObjectMaxSizeKb = cacheObjectMaxSizeKb;
        return this;
    }

    public int getCacheTTLSec() {
        return cacheTTLSec;
    }

    public ContextProperties setCacheTTLSec(int cacheTTLSec) {
        this.cacheTTLSec = cacheTTLSec;
        return this;
    }

    public boolean isCachingAllowed() {
        return cachingAllowed;
    }

    public ContextProperties setCachingAllowed(boolean cachingAllowed) {
        this.cachingAllowed = cachingAllowed;
        return this;
    }

    public int getUnloadDelayMs() {
        return unloadDelayMs;
    }

    public ContextProperties setUnloadDelayMs(int unloadDelayMs) {
        this.unloadDelayMs = unloadDelayMs;
        return this;
    }

    public int getMaxActiveSessions() {
        return maxActiveSessions;
    }

    public ContextProperties setMaxActiveSessions(int maxActiveSessions) {
        this.maxActiveSessions = maxActiveSessions;
        return this;
    }

    public int getSessionTimeoutMinutes() {
        return sessionTimeoutMinutes;
    }

    public ContextProperties setSessionTimeoutMinutes(int sessionTimeoutMinutes) {
        this.sessionTimeoutMinutes = sessionTimeoutMinutes;
        return this;
    }

    public String getPostCharacterEncoding() {
        return postCharacterEncoding;
    }

    public ContextProperties setPostCharacterEncoding(String postCharacterEncoding) {
        this.postCharacterEncoding = postCharacterEncoding;
        return this;
    }
}
