package ru.concerteza.springtomcat.etomcat8.components;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: alexey
 * Date: 8/28/11
 */
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = -8124821589871278803L;
    public static final String CONTENT = "EmbeddedXmlSpringContext";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = (ApplicationContext) req.getSession().getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        resp.getOutputStream().write(context.getClass().getSimpleName().getBytes("UTF-8"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("foo");
        boolean res = "bar".equals(param);
        resp.getOutputStream().write(Boolean.toString(res).getBytes("UTF-8"));
    }
}
