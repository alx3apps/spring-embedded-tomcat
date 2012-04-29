package ru.concerteza.springtomcat.components.registry.concurrent;

import javax.servlet.http.HttpSession;

/**
 * User: alexey
 * Date: 4/29/12
 */
public class InvalidateExistedStrategy implements ConcurrentSessionStrategy {
    @Override
    public void onExisted(HttpSession existed, HttpSession current) throws ConcurrentSessionException {
        existed.invalidate();
    }
}
