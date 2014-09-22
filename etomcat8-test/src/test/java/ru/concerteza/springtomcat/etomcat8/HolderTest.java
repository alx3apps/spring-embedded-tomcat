package ru.concerteza.springtomcat.etomcat8;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * User: alexey
 * Date: 3/2/12
 */
public class HolderTest extends TestSupertype {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testAccessLog() throws Exception {
        HttpClient client = new HttpClient();
        // test get
        HttpMethod get = new GetMethod("http://127.0.0.1:8080/etomcat_test");
        client.executeMethod(get);
        // test data
        byte[] responseBody = get.getResponseBody();
        String content = new String(responseBody, "UTF-8");
        assertTrue(content.matches("^[A-Z0-9]{32}$"));
    }

    @Override
    protected String dirname() {
        return "holderdir";
    }
}

