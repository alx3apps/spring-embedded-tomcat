package ru.concerteza.springtomcat.components.access;

import org.joda.time.LocalDateTime;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class AccessLogFilter extends GenericFilterBean {
    private AccessConsumer consumer;

    // todo: make listenable
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
        return new AccessEvent(start, end, request.getRemoteAddr(), request.getMethod(), request.getRequestURI());
    }
}
