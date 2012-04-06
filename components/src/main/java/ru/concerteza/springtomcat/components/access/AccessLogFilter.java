package ru.concerteza.springtomcat.components.access;

import org.apache.catalina.connector.ResponseFacade;
import org.apache.commons.lang.UnhandledException;
import org.joda.time.LocalDateTime;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class AccessLogFilter extends GenericFilterBean {
    private static final Pattern CN_PATTERN = Pattern.compile("CN=(.*?),", Pattern.CASE_INSENSITIVE);
    private static final Field RESPONSE_FIELD;
    static {
        try {
            RESPONSE_FIELD = ResponseFacade.class.getDeclaredField("response");
            RESPONSE_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new UnhandledException(e);
        }
    }

    private AccessConsumer consumer;

    // don't want to use listeners here - they will be hard to configure from spring
    public void setConsumer(AccessConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        LocalDateTime start = new LocalDateTime();
        chain.doFilter(request, response);
        LocalDateTime end = new LocalDateTime();
        AccessEvent event = createEvent(req, resp, start, end);
        consumer.consume(event);
    }

    private AccessEvent createEvent(HttpServletRequest request, HttpServletResponse response, LocalDateTime start, LocalDateTime end) {
        int status = extractResponseCode(response);
        String cn = extractCN(request);
        return new AccessEvent(start, end, request.getRemoteAddr(), request.getMethod(), status, cn, request.getRequestURI());
    }

    // in servlet API 2.5 it's better then response wrapping
    private int extractResponseCode(HttpServletResponse response) {
        try {
            ServletResponse temp = response;
            while (ServletResponseWrapper.class.isAssignableFrom(temp.getClass())) {
                ServletResponseWrapper wrapper = (ServletResponseWrapper) temp;
                temp = wrapper.getResponse();
            }
            if (!ResponseFacade.class.isAssignableFrom(temp.getClass())) return -1;
            org.apache.catalina.connector.Response resp = (org.apache.catalina.connector.Response) RESPONSE_FIELD.get(temp);
            return resp.getStatus();
        } catch (IllegalAccessException e) {
            throw new UnhandledException(e);
        }
    }

    private String extractCN(HttpServletRequest req) {
        X509Certificate[] certs = (X509Certificate[]) req.getAttribute("javax.servlet.request.X509Certificate");
        if (null != certs && certs.length > 0) {
            return parseDN(certs[0]);
        }
        return "NO_CERT_FOUND";
    }

    private String parseDN(X509Certificate cert) {
        String dn = cert.getSubjectDN().getName();
        Matcher matcher = CN_PATTERN.matcher(dn);
        boolean findCn = matcher.find();
        if (!findCn) throw new IllegalArgumentException(String.format("Cannot find CN part in DN: '%s'", dn));
        return matcher.group(1);
    }
}
