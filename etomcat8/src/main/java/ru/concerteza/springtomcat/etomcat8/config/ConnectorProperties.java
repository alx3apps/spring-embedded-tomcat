package ru.concerteza.springtomcat.etomcat8.config;

/**
 * User: alexey
 * Date: 11/4/11
 */

// http://tomcat.apache.org/tomcat-6.0-doc/config/http.html
public class ConnectorProperties {
    private boolean enableLookups = false;
    private int maxPostSizeBytes = 2097152;
    private String uriEncoding = "UTF-8";
    private int maxSavePostSizeBytes = 4096;
    private int acceptCount = 1000;
    private String compressableMimeType = "text/html,text/xml,text/plain";
    private String compression = "off";
    private int compressionMinSizeBytes = 2048;
    private String noCompressionUserAgents = "";
    private boolean disableUploadTimeout = false;
    private int maxHttpHeaderSizeBytes = 8192;
    private int maxKeepAliveRequests = 100;
    private String server = "Embedded Servlet Container";
    private int socketBufferBytes = 9000;
    private boolean useComet = false;
    private boolean useFailFastResponseWriter = false;

    public boolean isEnableLookups() {
        return enableLookups;
    }

    public ConnectorProperties setEnableLookups(boolean enableLookups) {
        this.enableLookups = enableLookups;
        return this;
    }

    public int getMaxPostSizeBytes() {
        return maxPostSizeBytes;
    }

    public ConnectorProperties setMaxPostSizeBytes(int maxPostSizeBytes) {
        this.maxPostSizeBytes = maxPostSizeBytes;
        return this;
    }

    public String getUriEncoding() {
        return uriEncoding;
    }

    public ConnectorProperties setUriEncoding(String uriEncoding) {
        this.uriEncoding = uriEncoding;
        return this;
    }

    public int getMaxSavePostSizeBytes() {
        return maxSavePostSizeBytes;
    }

    public ConnectorProperties setMaxSavePostSizeBytes(int maxSavePostSizeBytes) {
        this.maxSavePostSizeBytes = maxSavePostSizeBytes;
        return this;
    }

    public int getAcceptCount() {
        return acceptCount;
    }

    public ConnectorProperties setAcceptCount(int acceptCount) {
        this.acceptCount = acceptCount;
        return this;
    }

    public String getCompressableMimeType() {
        return compressableMimeType;
    }

    public ConnectorProperties setCompressableMimeType(String compressableMimeType) {
        this.compressableMimeType = compressableMimeType;
        return this;
    }

    public String getCompression() {
        return compression;
    }

    public ConnectorProperties setCompression(String compression) {
        this.compression = compression;
        return this;
    }

    public int getCompressionMinSizeBytes() {
        return compressionMinSizeBytes;
    }

    public ConnectorProperties setCompressionMinSizeBytes(int compressionMinSizeBytes) {
        this.compressionMinSizeBytes = compressionMinSizeBytes;
        return this;
    }

    public String getNoCompressionUserAgents() {
        return noCompressionUserAgents;
    }

    public ConnectorProperties setNoCompressionUserAgents(String noCompressionUserAgents) {
        this.noCompressionUserAgents = noCompressionUserAgents;
        return this;
    }

    public boolean isDisableUploadTimeout() {
        return disableUploadTimeout;
    }

    public ConnectorProperties setDisableUploadTimeout(boolean disableUploadTimeout) {
        this.disableUploadTimeout = disableUploadTimeout;
        return this;
    }

    public int getMaxHttpHeaderSizeBytes() {
        return maxHttpHeaderSizeBytes;
    }

    public ConnectorProperties setMaxHttpHeaderSizeBytes(int maxHttpHeaderSizeBytes) {
        this.maxHttpHeaderSizeBytes = maxHttpHeaderSizeBytes;
        return this;
    }

    public int getMaxKeepAliveRequests() {
        return maxKeepAliveRequests;
    }

    public ConnectorProperties setMaxKeepAliveRequests(int maxKeepAliveRequests) {
        this.maxKeepAliveRequests = maxKeepAliveRequests;
        return this;
    }

    public String getServer() {
        return server;
    }

    public ConnectorProperties setServer(String server) {
        this.server = server;
        return this;
    }

    public int getSocketBufferBytes() {
        return socketBufferBytes;
    }

    public ConnectorProperties setSocketBufferBytes(int socketBufferBytes) {
        this.socketBufferBytes = socketBufferBytes;
        return this;
    }

    public boolean isUseComet() {
        return useComet;
    }

    public ConnectorProperties setUseComet(boolean useComet) {
        this.useComet = useComet;
        return this;
    }

    public boolean isUseFailFastResponseWriter() {
        return useFailFastResponseWriter;
    }

    public ConnectorProperties setUseFailFastResponseWriter(boolean useFailFastResponseWriter) {
        this.useFailFastResponseWriter = useFailFastResponseWriter;
        return this;
    }
}
