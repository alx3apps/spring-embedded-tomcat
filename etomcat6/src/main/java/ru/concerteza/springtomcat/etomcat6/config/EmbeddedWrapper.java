package ru.concerteza.springtomcat.etomcat6.config;

import org.apache.catalina.core.StandardWrapper;

import javax.servlet.ServletException;

/**
 * User: alexey
 * Date: 3/8/12
 */

// fail-fast-on-load servlet wrapper
public class EmbeddedWrapper extends StandardWrapper {
    private static final long serialVersionUID = -188901122539015077L;

    @Override
    public void load() throws ServletException {
        try {
            super.load();
        } catch (ServletException e) {
            throw new IllegalStateException(e);
        }
    }
}
