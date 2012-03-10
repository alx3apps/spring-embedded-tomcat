package ru.concerteza.springtomcat.etomcat6.config;

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
    private int maxKeepAliveRequests = 8192;
    private String server = "Embedded Servlet Container";
    private int socketBufferBytes = 9000;
    private boolean useComet = false;

    public boolean isEnableLookups() {
        return enableLookups;
    }

    public void setEnableLookups(boolean enableLookups) {
        this.enableLookups = enableLookups;
    }

    public int getMaxPostSizeBytes() {
        return maxPostSizeBytes;
    }

    public void setMaxPostSizeBytes(int maxPostSizeBytes) {
        this.maxPostSizeBytes = maxPostSizeBytes;
    }

    public String getUriEncoding() {
        return uriEncoding;
    }

    public void setUriEncoding(String uriEncoding) {
        this.uriEncoding = uriEncoding;
    }

    public int getMaxSavePostSizeBytes() {
        return maxSavePostSizeBytes;
    }

    public void setMaxSavePostSizeBytes(int maxSavePostSizeBytes) {
        this.maxSavePostSizeBytes = maxSavePostSizeBytes;
    }

    public int getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(int acceptCount) {
        this.acceptCount = acceptCount;
    }

    public String getCompressableMimeType() {
        return compressableMimeType;
    }

    public void setCompressableMimeType(String compressableMimeType) {
        this.compressableMimeType = compressableMimeType;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public int getCompressionMinSizeBytes() {
        return compressionMinSizeBytes;
    }

    public void setCompressionMinSizeBytes(int compressionMinSizeBytes) {
        this.compressionMinSizeBytes = compressionMinSizeBytes;
    }

    public String getNoCompressionUserAgents() {
        return noCompressionUserAgents;
    }

    public void setNoCompressionUserAgents(String noCompressionUserAgents) {
        this.noCompressionUserAgents = noCompressionUserAgents;
    }

    public boolean isDisableUploadTimeout() {
        return disableUploadTimeout;
    }

    public void setDisableUploadTimeout(boolean disableUploadTimeout) {
        this.disableUploadTimeout = disableUploadTimeout;
    }

    public int getMaxHttpHeaderSizeBytes() {
        return maxHttpHeaderSizeBytes;
    }

    public void setMaxHttpHeaderSizeBytes(int maxHttpHeaderSizeBytes) {
        this.maxHttpHeaderSizeBytes = maxHttpHeaderSizeBytes;
    }

    public int getMaxKeepAliveRequests() {
        return maxKeepAliveRequests;
    }

    public void setMaxKeepAliveRequests(int maxKeepAliveRequests) {
        this.maxKeepAliveRequests = maxKeepAliveRequests;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getSocketBufferBytes() {
        return socketBufferBytes;
    }

    public void setSocketBufferBytes(int socketBufferBytes) {
        this.socketBufferBytes = socketBufferBytes;
    }

    public boolean isUseComet() {
        return useComet;
    }

    public void setUseComet(boolean useComet) {
        this.useComet = useComet;
    }
}
