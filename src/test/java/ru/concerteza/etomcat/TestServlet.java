package ru.concerteza.etomcat;

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
    public static final String CONTENT = "ETomcat is running";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getOutputStream().write(CONTENT.getBytes("UTF-8"));
    }
}
