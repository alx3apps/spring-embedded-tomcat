package ru.concerteza.etomcat;

import org.apache.catalina.Executor;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Embedded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * User: alexey
 * Date: 8/30/11
 */
public class EmbeddedHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Embedded embedded;
    private final Executor executor;

    public EmbeddedHandler(Embedded embedded, Executor executor) {
        this.embedded = embedded;
        this.executor = executor;
    }

    public void stop() {
        try {
            embedded.stop();
        } catch (Exception e) {
            logger.error("Error stopping container", e);
        }
        try {
            executor.stop();
        } catch (Exception e) {
            logger.error("Error stopping executor", e);
        }
    }
}
