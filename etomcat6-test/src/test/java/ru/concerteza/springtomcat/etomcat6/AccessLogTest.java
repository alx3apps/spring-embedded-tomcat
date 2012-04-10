package ru.concerteza.springtomcat.etomcat6;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import ru.concerteza.springtomcat.components.access.AccessEvent;
import ru.concerteza.springtomcat.etomcat6.components.TestLogConsumer;
import ru.concerteza.springtomcat.etomcat6.components.TestServlet;
import ru.concerteza.springtomcat.etomcat6.context.EmbeddedAnnotationSpringContext;

import java.util.Arrays;
import java.util.List;

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
