package ru.concerteza.springtomcat.etomcat6.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import ru.concerteza.springtomcat.components.holder.SessionHolder;
import ru.concerteza.springtomcat.components.registry.SessionRegistry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: alexey
 * Date: 3/2/12
 */
public class RegistryServlet extends HttpServlet {
    private static final long serialVersionUID = 4131885764840516610L;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = (ApplicationContext) req.getSession().getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        SessionRegistry registry = context.getBean(SessionRegistry.class);
        logger.info(registry.dump());
        resp.getOutputStream().write(Boolean.toString(registry.contains("anonymous")).getBytes("UTF-8"));
    }
}
