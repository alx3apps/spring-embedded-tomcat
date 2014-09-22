package ru.concerteza.springtomcat.etomcat8;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.concerteza.springtomcat.etomcat8.components.TestFilter;
import ru.concerteza.springtomcat.etomcat8.components.TestListener;
import ru.concerteza.springtomcat.etomcat8.components.TestServlet;

/**
 * User: alexey
 * Date: 8/28/11
 */

public class AppTest extends TestSupertype {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testRunning() throws Exception {
        HttpClient client = new HttpClient();
        setupSsl();
        // test get
        HttpMethod get = new GetMethod("https://127.0.0.1:8443/etomcat_test");
        get.getParams().setCookiePolicy(CookiePolicy.RFC_2965);
//        Thread.sleep(10000000);
        client.executeMethod(get);
        byte[] responseBody = get.getResponseBody();
        String content = new String(responseBody, "UTF-8");
        Assert.assertEquals("Servlet get fail", TestServlet.CONTENT, content);
        Assert.assertTrue("Listener fail", TestListener.isInitialized());
        Assert.assertTrue("Filter init fail", TestFilter.isInitialized());
        Assert.assertTrue("Filter fail", TestFilter.isFiltered());
        // test post
        PostMethod post = new PostMethod("https://127.0.0.1:8443/etomcat_test");
        post.getParams().setCookiePolicy(CookiePolicy.RFC_2965);
        NameValuePair[] data = {
                new NameValuePair("foo", "bar"),
        };
        post.setRequestBody(data);
        client.executeMethod(post);
        byte[] postBody = post.getResponseBody();
        String res = new String(postBody, "UTF-8");
        Assert.assertEquals("Servlet post fail", Boolean.TRUE.toString(), res);
        // test 404
        GetMethod fail = new GetMethod("https://127.0.0.1:8443/foobar");
        client.executeMethod(fail);
        Assert.assertEquals("Wrong result code", 404, fail.getStatusCode());
    }

    @Override
    protected String dirname() {
        return "appdir";
    }
}
