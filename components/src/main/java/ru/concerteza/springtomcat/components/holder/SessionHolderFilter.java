package ru.concerteza.springtomcat.components.holder;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class SessionHolderFilter extends GenericFilterBean {
    private SessionHolder holder;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            holder.set(req.getSession());
            chain.doFilter(request, response);
        } finally {
            holder.remove();
        }
    }

    public void setHolder(SessionHolder holder) {
        this.holder = holder;
    }
}
