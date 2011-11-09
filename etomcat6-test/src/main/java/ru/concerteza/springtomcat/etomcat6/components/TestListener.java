package ru.concerteza.springtomcat.etomcat6.components;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * User: alexey
 * Date: 8/29/11
 */
public class TestListener implements ServletContextListener {
    private static boolean initialized = false;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initialized = true;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // this line is initially left blank
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
