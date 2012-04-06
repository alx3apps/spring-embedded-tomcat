package ru.concerteza.springtomcat.components.access;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class AccessEvent implements Serializable{
    private static final long serialVersionUID = -4507075747441342464L;

    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String remoteIpAddress;
    private final String requestMethod;
    private final int responseCode;
    private final String commonName;
    private final String url;

    public AccessEvent(LocalDateTime start, LocalDateTime end, String remoteIpAddress, String requestMethod, int responseCode, String commonName, String url) {
        this.start = start;
        this.end = end;
        this.remoteIpAddress = remoteIpAddress;
        this.requestMethod = requestMethod;
        this.responseCode = responseCode;
        this.commonName = commonName;
        this.url = url;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getRemoteIpAddress() {
        return remoteIpAddress;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AccessEvent that = (AccessEvent) obj;
        return new EqualsBuilder()
                .append(this.start, that.start)
                .append(this.end, that.end)
                .append(this.remoteIpAddress, that.remoteIpAddress)
                .append(this.requestMethod, that.requestMethod)
                .append(this.responseCode, that.responseCode)
                .append(this.commonName, that.commonName)
                .append(this.url, that.url)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(start)
                .append(end)
                .append(remoteIpAddress)
                .append(requestMethod)
                .append(responseCode)
                .append(commonName)
                .append(url)
                .hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("start", start).
                append("end", end).
                append("remoteIpAddress", remoteIpAddress).
                append("requestMethod", requestMethod).
                append("responseCode", responseCode).
                append("commonName", commonName).
                append("url", url).
                toString();
    }
}
