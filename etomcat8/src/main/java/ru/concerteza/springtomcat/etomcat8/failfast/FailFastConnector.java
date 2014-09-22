package ru.concerteza.springtomcat.etomcat8.failfast;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Response;

/**
 * User: alexkasko
 * Date: 4/8/14
 */
public class FailFastConnector extends Connector {
    public FailFastConnector() throws Exception {
    }

    public FailFastConnector(String protocol) throws Exception {
        super(protocol);
    }

    @Override
    public Response createResponse() {
        Response response = new FailFastResponse();
        response.setConnector(this);
        return (response);
    }
}
