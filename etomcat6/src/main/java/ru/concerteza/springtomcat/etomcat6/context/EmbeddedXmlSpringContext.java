package ru.concerteza.springtomcat.etomcat6.context;

import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * User: alexey
 * Date: 7/24/11
 */
public class EmbeddedXmlSpringContext extends AbstractXmlApplicationContext implements EmbeddedSpringContext {

    private ServletContext servletContext;

    public EmbeddedXmlSpringContext(String configLocation) throws BeansException {
        super(null);
        setConfigLocations(new String[]{configLocation});
        refresh();
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void bind(ServletContext servletContext) {
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this);
        this.servletContext = servletContext;
    }
}
