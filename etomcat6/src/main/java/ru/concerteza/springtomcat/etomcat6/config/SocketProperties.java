package ru.concerteza.springtomcat.etomcat6.config;

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

    public SocketProperties setDirectBuffer(boolean directBuffer) {
        this.directBuffer = directBuffer;
        return this;
    }

    public int getRxBufSizeBytes() {
        return rxBufSizeBytes;
    }

    public SocketProperties setRxBufSizeBytes(int rxBufSizeBytes) {
        this.rxBufSizeBytes = rxBufSizeBytes;
        return this;
    }

    public int getTxBufSizeBytes() {
        return txBufSizeBytes;
    }

    public SocketProperties setTxBufSizeBytes(int txBufSizeBytes) {
        this.txBufSizeBytes = txBufSizeBytes;
        return this;
    }

    public int getAppReadBufSizeBytes() {
        return appReadBufSizeBytes;
    }

    public SocketProperties setAppReadBufSizeBytes(int appReadBufSizeBytes) {
        this.appReadBufSizeBytes = appReadBufSizeBytes;
        return this;
    }

    public int getAppWriteBufSizeBytes() {
        return appWriteBufSizeBytes;
    }

    public SocketProperties setAppWriteBufSizeBytes(int appWriteBufSizeBytes) {
        this.appWriteBufSizeBytes = appWriteBufSizeBytes;
        return this;
    }

    public int getBufferPool() {
        return bufferPool;
    }

    public SocketProperties setBufferPool(int bufferPool) {
        this.bufferPool = bufferPool;
        return this;
    }

    public int getBufferPoolSizeBytes() {
        return bufferPoolSizeBytes;
    }

    public SocketProperties setBufferPoolSizeBytes(int bufferPoolSizeBytes) {
        this.bufferPoolSizeBytes = bufferPoolSizeBytes;
        return this;
    }

    public int getProcessorCache() {
        return processorCache;
    }

    public SocketProperties setProcessorCache(int processorCache) {
        this.processorCache = processorCache;
        return this;
    }

    public int getKeyCache() {
        return keyCache;
    }

    public SocketProperties setKeyCache(int keyCache) {
        this.keyCache = keyCache;
        return this;
    }

    public int getEventCache() {
        return eventCache;
    }

    public SocketProperties setEventCache(int eventCache) {
        this.eventCache = eventCache;
        return this;
    }

    public boolean isTcpNoDelay() {
        return tcpNoDelay;
    }

    public SocketProperties setTcpNoDelay(boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
        return this;
    }

    public boolean isSoKeepAlive() {
        return soKeepAlive;
    }

    public SocketProperties setSoKeepAlive(boolean soKeepAlive) {
        this.soKeepAlive = soKeepAlive;
        return this;
    }

    public boolean isOoBInline() {
        return ooBInline;
    }

    public SocketProperties setOoBInline(boolean ooBInline) {
        this.ooBInline = ooBInline;
        return this;
    }

    public boolean isSoReuseAddress() {
        return soReuseAddress;
    }

    public SocketProperties setSoReuseAddress(boolean soReuseAddress) {
        this.soReuseAddress = soReuseAddress;
        return this;
    }

    public boolean isSoLingerOn() {
        return soLingerOn;
    }

    public SocketProperties setSoLingerOn(boolean soLingerOn) {
        this.soLingerOn = soLingerOn;
        return this;
    }

    public int getSoLingerTimeSec() {
        return soLingerTimeSec;
    }

    public SocketProperties setSoLingerTimeSec(int soLingerTimeSec) {
        this.soLingerTimeSec = soLingerTimeSec;
        return this;
    }

    public int getSoTimeoutMs() {
        return soTimeoutMs;
    }

    public SocketProperties setSoTimeoutMs(int soTimeoutMs) {
        this.soTimeoutMs = soTimeoutMs;
        return this;
    }

    public int getSoTrafficClass() {
        return soTrafficClass;
    }

    public SocketProperties setSoTrafficClass(int soTrafficClass) {
        this.soTrafficClass = soTrafficClass;
        return this;
    }

    public int getPerformanceConnectionTime() {
        return performanceConnectionTime;
    }

    public SocketProperties setPerformanceConnectionTime(int performanceConnectionTime) {
        this.performanceConnectionTime = performanceConnectionTime;
        return this;
    }

    public int getPerformanceLatency() {
        return performanceLatency;
    }

    public SocketProperties setPerformanceLatency(int performanceLatency) {
        this.performanceLatency = performanceLatency;
        return this;
    }

    public int getPerformanceBandwidth() {
        return performanceBandwidth;
    }

    public SocketProperties setPerformanceBandwidth(int performanceBandwidth) {
        this.performanceBandwidth = performanceBandwidth;
        return this;
    }

    public int getUnlockTimeoutMs() {
        return unlockTimeoutMs;
    }

    public SocketProperties setUnlockTimeoutMs(int unlockTimeoutMs) {
        this.unlockTimeoutMs = unlockTimeoutMs;
        return this;
    }
}
