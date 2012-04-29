package ru.concerteza.springtomcat.components.registry.concurrent;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: alexey
 * Date: 4/29/12
 */
public class RemoteIpFilter extends GenericFilterBean {
    public static final String REMOTE_IP_ATTRIBUTE = RemoteIpFilter.class.getName() + ".remoteIp";
    private String remoteIpHeader = "X-Forwarded-For";

    public void setRemoteIpHeader(String remoteIpHeader) {
        this.remoteIpHeader = remoteIpHeader;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String existed = (String) session.getAttribute(REMOTE_IP_ATTRIBUTE);
        String current = extractRemoteIp(req);
        if(null != existed) {
            if(!existed.equals(current)) throw new IllegalStateException(
                    "Another remote IP found in session, current IP: " + current + ", existed IP: " + existed);
        } else {
            session.setAttribute(REMOTE_IP_ATTRIBUTE, current);
        }
        chain.doFilter(request, response);
    }

    private String extractRemoteIp(HttpServletRequest req) {
        String forwarded = req.getHeader(remoteIpHeader);
        return null != forwarded ? forwarded : req.getRemoteAddr();
    }
}
