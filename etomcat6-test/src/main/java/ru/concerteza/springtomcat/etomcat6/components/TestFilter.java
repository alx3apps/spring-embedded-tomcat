package ru.concerteza.springtomcat.etomcat6.components;

import javax.servlet.*;
import java.io.IOException;

/**
 * User: alexey
 * Date: 8/29/11
 */
public class TestFilter implements Filter {
    private static boolean initialized;
    private static boolean filtered;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initialized = true;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        filtered = true;
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // this line is initially left blank
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static boolean isFiltered() {
        return filtered;
    }
}
