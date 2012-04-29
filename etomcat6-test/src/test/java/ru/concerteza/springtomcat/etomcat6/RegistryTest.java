package ru.concerteza.springtomcat.etomcat6;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: alexey
 * Date: 3/2/12
 */
public class RegistryTest extends TestSupertype {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testRegistry() throws Exception {
        {
            HttpClient client = new HttpClient();
            // test get
            HttpMethod get = new GetMethod("http://127.0.0.1:8080/etomcat_test");
            client.executeMethod(get);
            // test data
            byte[] responseBody = get.getResponseBody();
            String content = new String(responseBody, "UTF-8");
            assertEquals("Registry fail", "true", content);
        }
        {
            // concurrent test
            HttpClient client = new HttpClient();
            HttpMethod get = new GetMethod("http://127.0.0.1:8080/etomcat_test");
            get.addRequestHeader("X-Forwarded-For", "192.168.42.42");
            client.executeMethod(get);
            assertEquals(HttpServletResponse.SC_UNAUTHORIZED, get.getStatusCode());
            byte[] responseBody = get.getResponseBody();
            String content = new String(responseBody, "UTF-8");
            assertEquals("127.0.0.1", content);
        }
    }

    @Override
    protected String dirname() {
        return "registrydir";
    }
}

