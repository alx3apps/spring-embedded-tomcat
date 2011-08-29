package ru.concerteza.etomcat;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.jsslutils.extra.apachehttpclient.SslContextedSecureProtocolSocketFactory;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ru.concerteza.etomcat.app.TestFilter;
import ru.concerteza.etomcat.app.TestListener;
import ru.concerteza.etomcat.app.TestServlet;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

import static java.io.File.separator;

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
        HttpMethod method = new GetMethod("https://127.0.0.1:8443/etomcat_test");
//        Thread.sleep(10000000);
        client.executeMethod(method);
        byte[] responseBody = method.getResponseBody();
        String content = new String(responseBody, "UTF-8");
        Assert.assertEquals("Servlet fail", TestServlet.CONTENT, content);
        Assert.assertTrue("Listener fail", TestListener.isInitialized());
        Assert.assertTrue("Filter init fail", TestFilter.isInitialized());
        Assert.assertTrue("Filter fail", TestFilter.isFiltered());
    }

    @Override
    protected String dirname() {
        return "appdir";
    }
}
