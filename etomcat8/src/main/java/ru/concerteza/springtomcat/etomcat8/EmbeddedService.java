package ru.concerteza.springtomcat.etomcat8;

import org.apache.catalina.Executor;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardService;

/**
 * @author alexkasko
 *         Date: 9/22/14
 */
public class EmbeddedService extends StandardService {
    @Override
    protected void startInternal() throws LifecycleException {
        setState(LifecycleState.STARTING);
        container.start();
        for (Executor executor : executors) {
            executor.start();
        }
        mapperListener.start();
        for (Connector connector : connectors) {
            connector.start();
        }
    }
}
