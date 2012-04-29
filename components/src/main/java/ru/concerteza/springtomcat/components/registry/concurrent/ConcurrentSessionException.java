package ru.concerteza.springtomcat.components.registry.concurrent;

/**
 * User: alexey
 * Date: 4/29/12
 */
public class ConcurrentSessionException extends RuntimeException {
    private static final long serialVersionUID = 3786976580626788476L;

    public ConcurrentSessionException(String message) {
        super(message);
    }
}
