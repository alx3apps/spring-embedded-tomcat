package ru.concerteza.springtomcat.components.registry;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.concerteza.springtomcat.components.registry.concurrent.ConcurrentSessionException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: alexey
 * Date: 11/4/11
 */

// depends on Spring Security
//
public class SessionIdRegistryFilter extends GenericFilterBean {
    private SessionRegistry registry;

    public void setRegistry(SessionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(null == auth) throw new IllegalStateException("Auth info not found on sessionRegistryFilter step, sessionId: " + req.getSession().getId());
        try {
            registry.put(auth.getName(), req.getSession());
            chain.doFilter(request, response);
        } catch (ConcurrentSessionException e) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write(e.getMessage());
//            tomcat 401 error page here
//            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }
}
