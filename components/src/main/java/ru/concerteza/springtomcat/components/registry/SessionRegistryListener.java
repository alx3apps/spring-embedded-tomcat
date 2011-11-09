package ru.concerteza.springtomcat.components.registry;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class SessionRegistryListener implements HttpSessionListener {
    // todo context access
    private SessionRegistry registry;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // this method is initially left blank
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //Todo change body of implemented methods use File | Settings | File Templates.
    }

    public void setRegistry(SessionRegistry registry) {
        this.registry = registry;
    }
}
