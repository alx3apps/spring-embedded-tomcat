package ru.concerteza.etomcat;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import ru.concerteza.etomcat.app.TestFilter;
import ru.concerteza.etomcat.app.TestListener;
import ru.concerteza.etomcat.app.TestServlet;

import java.io.File;

import static java.io.File.separator;

/**
* User: alexey
* Date: 8/29/11
*/
public class WebAppTest extends TestSupertype {

    @Test
    public void testRunning() throws Exception {
        HttpClient client = new HttpClient();
        setupSsl();
        HttpMethod method = new GetMethod("https://127.0.0.1:8443/static.html");
//        Thread.sleep(10000000);
        client.executeMethod(method);
        byte[] responseBody = method.getResponseBody();
        String content = new String(responseBody, "UTF-8");
        Assert.assertEquals("Static fail", "<html>static content</html>", content);
    }

    @Override
    protected String dirname() {
        return "webappdir";
    }
}
