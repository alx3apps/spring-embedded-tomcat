package ru.concerteza.etomcat;

import org.springframework.beans.BeansException;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * User: alexey
 * Date: 7/24/11
 */
class EmbeddedSpringContext extends FileSystemXmlApplicationContext implements WebApplicationContext {

    private ServletContext servletContext;

    public EmbeddedSpringContext(String configLocation) throws BeansException {
        super(configLocation);
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    public void bind(ServletContext servletContext) {
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this);
        this.servletContext = servletContext;
    }
}
