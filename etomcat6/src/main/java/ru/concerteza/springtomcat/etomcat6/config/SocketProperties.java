package ru.concerteza.springtomcat.etomcat6.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class SocketProperties {
    private boolean directBuffer = false;
    private int rxBufSizeBytes = 25188;
    private int txBufSizeBytes = 43800;
    private int appReadBufSizeBytes = 8192;
    private int appWriteBufSizeBytes = 8192;
    private int bufferPool = 500;
    private int bufferPoolSizeBytes = 104857600;
    private int processorCache = 500;
    private int keyCache = 500;
    private int eventCache = 500;
    private boolean tcpNoDelay = false;
    private boolean soKeepAlive = false;
    private boolean ooBInline = true;
    private boolean soReuseAddress = true;
    private boolean soLingerOn = true;
    private int soLingerTimeSec = 25;
    private int soTimeoutMs = 5000;
    private int soTrafficClass = 28;
    private int performanceConnectionTime = 1;
    private int performanceLatency = 0;
    private int performanceBandwidth = 1;
    private int unlockTimeoutMs = 250;

    public boolean isDirectBuffer() {
        return directBuffer;
    }

    public void setDirectBuffer(boolean directBuffer) {
        this.directBuffer = directBuffer;
    }

    public int getRxBufSizeBytes() {
        return rxBufSizeBytes;
    }

    public void setRxBufSizeBytes(int rxBufSizeBytes) {
        this.rxBufSizeBytes = rxBufSizeBytes;
    }

    public int getTxBufSizeBytes() {
        return txBufSizeBytes;
    }

    public void setTxBufSizeBytes(int txBufSizeBytes) {
        this.txBufSizeBytes = txBufSizeBytes;
    }

    public int getAppReadBufSizeBytes() {
        return appReadBufSizeBytes;
    }

    public void setAppReadBufSizeBytes(int appReadBufSizeBytes) {
        this.appReadBufSizeBytes = appReadBufSizeBytes;
    }

    public int getAppWriteBufSizeBytes() {
        return appWriteBufSizeBytes;
    }

    public void setAppWriteBufSizeBytes(int appWriteBufSizeBytes) {
        this.appWriteBufSizeBytes = appWriteBufSizeBytes;
    }

    public int getBufferPool() {
        return bufferPool;
    }

    public void setBufferPool(int bufferPool) {
        this.bufferPool = bufferPool;
    }

    public int getBufferPoolSizeBytes() {
        return bufferPoolSizeBytes;
    }

    public void setBufferPoolSizeBytes(int bufferPoolSizeBytes) {
        this.bufferPoolSizeBytes = bufferPoolSizeBytes;
    }

    public int getProcessorCache() {
        return processorCache;
    }

    public void setProcessorCache(int processorCache) {
        this.processorCache = processorCache;
    }

    public int getKeyCache() {
        return keyCache;
    }

    public void setKeyCache(int keyCache) {
        this.keyCache = keyCache;
    }

    public int getEventCache() {
        return eventCache;
    }

    public void setEventCache(int eventCache) {
        this.eventCache = eventCache;
    }

    public boolean isTcpNoDelay() {
        return tcpNoDelay;
    }

    public void setTcpNoDelay(boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
    }

    public boolean isSoKeepAlive() {
        return soKeepAlive;
    }

    public void setSoKeepAlive(boolean soKeepAlive) {
        this.soKeepAlive = soKeepAlive;
    }

    public boolean isOoBInline() {
        return ooBInline;
    }

    public void setOoBInline(boolean ooBInline) {
        this.ooBInline = ooBInline;
    }

    public boolean isSoReuseAddress() {
        return soReuseAddress;
    }

    public void setSoReuseAddress(boolean soReuseAddress) {
        this.soReuseAddress = soReuseAddress;
    }

    public boolean isSoLingerOn() {
        return soLingerOn;
    }

    public void setSoLingerOn(boolean soLingerOn) {
        this.soLingerOn = soLingerOn;
    }

    public int getSoLingerTimeSec() {
        return soLingerTimeSec;
    }

    public void setSoLingerTimeSec(int soLingerTimeSec) {
        this.soLingerTimeSec = soLingerTimeSec;
    }

    public int getSoTimeoutMs() {
        return soTimeoutMs;
    }

    public void setSoTimeoutMs(int soTimeoutMs) {
        this.soTimeoutMs = soTimeoutMs;
    }

    public int getSoTrafficClass() {
        return soTrafficClass;
    }

    public void setSoTrafficClass(int soTrafficClass) {
        this.soTrafficClass = soTrafficClass;
    }

    public int getPerformanceConnectionTime() {
        return performanceConnectionTime;
    }

    public void setPerformanceConnectionTime(int performanceConnectionTime) {
        this.performanceConnectionTime = performanceConnectionTime;
    }

    public int getPerformanceLatency() {
        return performanceLatency;
    }

    public void setPerformanceLatency(int performanceLatency) {
        this.performanceLatency = performanceLatency;
    }

    public int getPerformanceBandwidth() {
        return performanceBandwidth;
    }

    public void setPerformanceBandwidth(int performanceBandwidth) {
        this.performanceBandwidth = performanceBandwidth;
    }

    public int getUnlockTimeoutMs() {
        return unlockTimeoutMs;
    }

    public void setUnlockTimeoutMs(int unlockTimeoutMs) {
        this.unlockTimeoutMs = unlockTimeoutMs;
    }
}
