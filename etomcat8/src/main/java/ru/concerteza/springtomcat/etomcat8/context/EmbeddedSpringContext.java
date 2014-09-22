package ru.concerteza.springtomcat.etomcat8.context;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * User: alexey
 * Date: 4/9/12
 */
public interface EmbeddedSpringContext extends WebApplicationContext {
    void bind(ServletContext servletContext);
}
