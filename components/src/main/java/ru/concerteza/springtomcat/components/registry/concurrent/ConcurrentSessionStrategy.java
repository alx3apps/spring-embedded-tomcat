package ru.concerteza.springtomcat.components.registry.concurrent;

import ru.concerteza.springtomcat.components.registry.SessionRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * User: alexey
 * Date: 4/29/12
 */
public interface ConcurrentSessionStrategy {
    // new session will replace existed if no exception thrown
    void onExisted(HttpSession existed, HttpSession current) throws ConcurrentSessionException;
}
