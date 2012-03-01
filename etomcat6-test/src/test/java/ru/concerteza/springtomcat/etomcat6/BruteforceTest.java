package ru.concerteza.springtomcat.etomcat6;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * User: alexey
 * Date: 3/1/12
 */
public class BruteforceTest extends TestSupertype {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testAccessLog() throws Exception {
        HttpClient client = new HttpClient();
        client.getParams().setAuthenticationPreemptive(true);
        Credentials failcreds = new UsernamePasswordCredentials("admin", "fail");
        Credentials wincreds = new UsernamePasswordCredentials("admin", "password");
        // brute
        client.getState().setCredentials(AuthScope.ANY, failcreds);
        HttpMethod get = new GetMethod("http://127.0.0.1:8080/etomcat_test");
        client.executeMethod(get);
        assertEquals("First access fail", 401, get.getStatusCode());
        client.executeMethod(get);
        assertEquals("Last access fail", 401, get.getStatusCode());
        // closed
        client.executeMethod(get);
        assertEquals("Improbable fail", 401, get.getStatusCode());
        // change appearance
        client.getState().setCredentials(AuthScope.ANY, wincreds);
        client.executeMethod(get);
        assertEquals("Bruteforce fail", 401, get.getStatusCode());
        // cooldown
        Thread.sleep(1000);
        client.executeMethod(get);
        assertEquals("Cooldowned access fail", 200, get.getStatusCode());
    }

    @Override
    protected String dirname() {
        return "bruteforcedir";
    }
}
