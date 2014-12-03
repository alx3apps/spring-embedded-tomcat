package ru.concerteza.springtomcat.etomcat8;

import org.apache.catalina.core.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ru.concerteza.springtomcat.etomcat8.config.*;
import ru.concerteza.springtomcat.etomcat8.context.EmbeddedSpringContext;

/**
 * User: alexey Date: 6/21/11
 */

public class EmbeddedTomcat extends EmbeddedTomcatStandalone implements ApplicationContextAware {

    private ApplicationContext springContext;

    @Override
    protected void bindContext(StandardContext context) {
        // spring binding
        EmbeddedSpringContext embeddedSpringContext = (EmbeddedSpringContext) springContext;
        embeddedSpringContext.bind(context.getServletContext());
    }

    // setters for spring
    @Override
    public void setApplicationContext(ApplicationContext springContext) {
        this.springContext = springContext;
    }

    @Override
    public EmbeddedTomcat setGeneralProps(GeneralProperties generalProps) {
        super.setGeneralProps(generalProps);
        return this;
    }

    @Override
    public EmbeddedTomcat setFsProps(FsProperties fsProps) {
        super.setFsProps(fsProps);
        return this;
    }

    @Override
    public EmbeddedTomcat setHostProps(HostProperties hostProps) {
        super.setHostProps(hostProps);
        return this;
    }

    @Override
    public EmbeddedTomcat setContextProps(ContextProperties contextProps) {
        super.setContextProps(contextProps);
        return this;
    }

    @Override
    public EmbeddedTomcat setConnectorProps(ConnectorProperties connectorProps) {
        super.setConnectorProps(connectorProps);
        return this;
    }

    @Override
    public EmbeddedTomcat setNioProps(NioProperties nioProps) {
        super.setNioProps(nioProps);
        return this;
    }

    @Override
    public EmbeddedTomcat setSocketProps(SocketProperties socketProps) {
        super.setSocketProps(socketProps);
        return this;
    }

    @Override
    public EmbeddedTomcat setSslProps(SslProperties sslProps) {
        super.setSslProps(sslProps);
        return this;
    }

    @Override
    public EmbeddedTomcat setExecutorProps(ExecutorProperties executorProps) {
        super.setExecutorProps(executorProps);
        return this;
    }
}
