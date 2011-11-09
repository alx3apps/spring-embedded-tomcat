package ru.concerteza.springtomcat.components.registry;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class SessionIdRegistryFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Todo change body of implemented methods use File | Settings | File Templates.
    }
}
