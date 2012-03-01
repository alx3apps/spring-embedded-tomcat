package ru.concerteza.springtomcat.etomcat6.components;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import ru.concerteza.springtomcat.components.holder.SessionHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: alexey
 * Date: 3/2/12
 */
public class HolderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = (ApplicationContext) req.getSession().getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        SessionHolder holder = context.getBean(SessionHolder.class);
        resp.getOutputStream().write(holder.get().getId().getBytes("UTF-8"));
    }
}
