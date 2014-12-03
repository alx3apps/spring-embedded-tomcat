package ru.concerteza.springtomcat.etomcat6;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.concerteza.springtomcat.components.access.AccessEvent;
import ru.concerteza.springtomcat.etomcat6.components.TestLogConsumer;
import ru.concerteza.springtomcat.etomcat6.components.TestServlet;

import static org.junit.Assert.assertEquals;

/**
 * User: alexey
 * Date: 3/1/12
 */
public class AccessLogTest extends TestSupertype {
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
        assertEquals("Servlet get fail", TestServlet.CONTENT, content);
        // test log event
        AccessEvent ev = TestLogConsumer.getLastEvent();
        assertEquals("Method log fail", "GET", ev.getRequestMethod());
        assertEquals("Status log fail", 200, ev.getResponseCode());
        assertEquals("URL log fail", "/etomcat_test", ev.getUrl());
    }

    @Override
    protected String dirname() {
        return "accessdir";
    }
}
