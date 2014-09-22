package ru.concerteza.springtomcat.etomcat8.components;

import ru.concerteza.springtomcat.components.access.AccessConsumer;
import ru.concerteza.springtomcat.components.access.AccessEvent;

/**
 * User: alexey
 * Date: 3/1/12
 */
public class TestLogConsumer implements AccessConsumer {
    private static AccessEvent lastEvent;

    @Override
    public void consume(AccessEvent event) {
        TestLogConsumer.lastEvent = event;
    }

    public static AccessEvent getLastEvent() {
        return lastEvent;
    }
}
