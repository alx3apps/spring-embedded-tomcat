package ru.concerteza.springtomcat.etomcat8;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

/**
 * User: alexey
 * Date: 3/2/12
 */
public class RegistryTest extends TestSupertype {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private AtomicReference<String> sessionCookie = new AtomicReference<String>();

    @Test
    public void testRegistry() throws Exception {
        {
            HttpClient client = new HttpClient();
            client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
            // test get
            HttpMethod get = new GetMethod("http://127.0.0.1:8080/etomcat_test");
            client.executeMethod(get);
            sessionCookie.compareAndSet(null, get.getResponseHeader("Set-Cookie").getValue());
            // test data
            byte[] responseBody = get.getResponseBody();
            String content = new String(responseBody, "UTF-8");
            assertEquals("Registry fail", "true", content);
            // subsequent requests
            HttpMethod get1 = new GetMethod("http://127.0.0.1:8080/etomcat_test");
            String cookie = sessionCookie.get();
            if(null != cookie) get1.setRequestHeader("Cookie", cookie);
            client.executeMethod(get1);
            sessionCookie.compareAndSet(null, get.getResponseHeader("Set-Cookie").getValue());
            assertEquals(HttpServletResponse.SC_OK, get1.getStatusCode());
        }
        {
            // cookie test
            HttpClient client = new HttpClient();
            client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
            HttpMethod get1 = new GetMethod("http://127.0.0.1:8080/etomcat_test");
            String cookie = sessionCookie.get();
            if(null != cookie) get1.setRequestHeader("Cookie", cookie);
            client.executeMethod(get1);
            assertEquals(HttpServletResponse.SC_OK, get1.getStatusCode());
        }
        {
            // concurrent test
            HttpClient client = new HttpClient();
            client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
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

