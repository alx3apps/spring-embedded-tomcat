package ru.concerteza.springtomcat.components.access;

/**
 * User: alexey
 * Date: 11/4/11
 */
public interface AccessConsumer {
    void consume(AccessEvent event);
}
