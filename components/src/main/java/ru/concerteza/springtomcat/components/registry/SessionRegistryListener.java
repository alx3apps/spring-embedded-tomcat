package ru.concerteza.springtomcat.components.registry;

import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * User: alexey
 * Date: 11/4/11
 */

public class SessionRegistryListener implements ApplicationListener<HttpSessionDestroyedEvent> {
    private SessionRegistry registry;

    public void setRegistry(SessionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void onApplicationEvent(HttpSessionDestroyedEvent event) {
        registry.remove(event.getSession());
    }
}
