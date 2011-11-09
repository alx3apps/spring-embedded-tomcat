package ru.concerteza.springtomcat.components.holder;

import javax.servlet.http.HttpSession;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class SessionHolder {
    private final ThreadLocal<HttpSession> threadLocal = new ThreadLocal<HttpSession>();

    public void set(HttpSession session) {
        threadLocal.set(session);
    }

    public HttpSession get() {
        return threadLocal.get();
    }

    public void remove() {
        threadLocal.remove();
    }
}
