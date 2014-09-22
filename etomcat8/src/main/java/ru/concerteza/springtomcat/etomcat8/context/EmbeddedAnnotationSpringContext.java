package ru.concerteza.springtomcat.etomcat8.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * User: alexey
 * Date: 4/9/12
 */
public class EmbeddedAnnotationSpringContext extends AnnotationConfigApplicationContext implements EmbeddedSpringContext {
    private ServletContext servletContext;

    public EmbeddedAnnotationSpringContext() {
    }

    public EmbeddedAnnotationSpringContext(Class<?>... annotatedClasses) {
        super(annotatedClasses);
    }

    public EmbeddedAnnotationSpringContext(String... basePackages) {
        super(basePackages);
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
