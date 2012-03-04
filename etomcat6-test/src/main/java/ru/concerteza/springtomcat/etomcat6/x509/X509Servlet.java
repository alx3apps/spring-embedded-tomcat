package ru.concerteza.springtomcat.etomcat6.x509;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: alexey
 * Date: 3/4/12
 */
public class X509Servlet extends HttpServlet {
    private static final long serialVersionUID = -4988677350741242648L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = (ApplicationContext) req.getSession().getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        SecuredService service = context.getBean(SecuredService.class);
        String reply = service.hello();
        resp.getOutputStream().write(reply.getBytes("UTF-8"));
    }
}
