package ru.concerteza.springtomcat.etomcat8;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.concerteza.springtomcat.etomcat8.context.EmbeddedAnnotationSpringContext;

import java.io.File;

/**
 * User: alexey
 * Date: 4/9/12
 */
public class AnnotationConfigTest extends AnnotationTestSupertype {

    @Test
    public void testRunning() throws Exception {
        HttpClient client = new HttpClient();
        HttpMethod get = new GetMethod("http://127.0.0.1:8080/etomcat_test");
        client.executeMethod(get);
        byte[] responseBody = get.getResponseBody();
        String content = new String(responseBody, "UTF-8");
        Assert.assertEquals("Servlet get fail", EmbeddedAnnotationSpringContext.class.getSimpleName(), content);
    }

    @Override
    protected String dirname() {
        return "annotationdir";
    }

    @Override
    protected Class<?> configClass() {
        return Config.class;
    }

    @Configuration
    public static class Config {
        @Bean(destroyMethod = "stop")
        public EmbeddedTomcat etomcat() {
            return new EmbeddedTomcat();
        }
    }
}
