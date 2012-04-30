package ru.concerteza.springtomcat.components.registry.concurrent;

import ru.concerteza.springtomcat.components.registry.SessionRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * User: alexey
 * Date: 4/29/12
 */
public interface ConcurrentSessionStrategy {
    void onExisted(SessionRegistry.LockBoundedManager manager, String login, HttpSession existed, HttpSession current) throws ConcurrentSessionException;
}
