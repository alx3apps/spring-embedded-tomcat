package ru.concerteza.springtomcat.etomcat8;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.concerteza.springtomcat.etomcat8.components.TestServlet;

/**
 * User: alexey
 * Date: 8/28/11
 */

public class NoSslTest extends TestSupertype {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testRunning() throws Exception {
        HttpClient client = new HttpClient();
        // test get
        HttpMethod get = new GetMethod("http://127.0.0.1:8080/etomcat_test");
//        Thread.sleep(10000000);
        client.executeMethod(get);
        byte[] responseBody = get.getResponseBody();
        String content = new String(responseBody, "UTF-8");
        Assert.assertEquals("Servlet get fail", TestServlet.CONTENT, content);
    }

    @Override
    protected String dirname() {
        return "nosslappdir";
    }
}
