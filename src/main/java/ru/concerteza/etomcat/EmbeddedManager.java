package ru.concerteza.etomcat;

import org.apache.catalina.Container;
import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.session.ManagerBase;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: alexey
 * Date: 8/27/11
 */
class EmbeddedManager extends ManagerBase {
    private int maxActiveSessions;
    private AtomicInteger rejectedSessions = new AtomicInteger(0);

    public int getMaxActiveSessions() {
        return maxActiveSessions;
    }

    public void setMaxActiveSessions(int maxActiveSessions) {
        this.maxActiveSessions = maxActiveSessions;
    }

    @Override
    public int getRejectedSessions() {
        return rejectedSessions.get();
    }

    @Override
    public void setRejectedSessions(int rejectedSessions) {
        this.rejectedSessions.set(rejectedSessions);
    }

    @Override
    public Session createSession(String sessionId) {
        if ((maxActiveSessions >= 0) && (sessions.size() >= maxActiveSessions)) {
            rejectedSessions.addAndGet(1);
            throw new IllegalStateException(sm.getString("standardManager.createSession.ise"));
        }
        return super.createSession(sessionId);
    }

    @Override
    public void load() throws ClassNotFoundException, IOException {
        // this method is initially left blank
    }

    @Override
    public void unload() throws IOException {
        // this method is initially left blank
    }
}
