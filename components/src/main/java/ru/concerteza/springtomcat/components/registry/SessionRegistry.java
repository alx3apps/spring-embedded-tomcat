package ru.concerteza.springtomcat.components.registry;

import org.apache.commons.lang.builder.ToStringBuilder;
import ru.concerteza.springtomcat.components.registry.concurrent.ConcurrentSessionStrategy;
import ru.concerteza.springtomcat.components.registry.concurrent.InvalidateExistedStrategy;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * User: alexey
 * Date: 11/4/11
 */

// <!-- http://static.springsource.org/spring-security/site/docs/3.0.x/reference/session-mgmt.html#concurrent-sessions -->
public class SessionRegistry {
    private final LockBoundedManager lockBoundedManager = new LockBoundedManager();
    // login to session map
    private final Map<String, HttpSession> authorMap = new HashMap<String, HttpSession>();
    // session id to login map
    private final Map<String, String> idMap = new HashMap<String, String>();
    private final Object lock = new Object();

    private ConcurrentSessionStrategy concurrentSessionStrategy = new InvalidateExistedStrategy();

    public void setConcurrentSessionStrategy(ConcurrentSessionStrategy concurrentSessionStrategy) {
        this.concurrentSessionStrategy = concurrentSessionStrategy;
    }

    public void put(String login, HttpSession session) {
        synchronized (lock) {
            HttpSession existed = authorMap.get(login);
            if(null != existed) {
                if(!existed.getId().equals(session.getId())) {
                    concurrentSessionStrategy.onExisted(lockBoundedManager, login, existed, session);
                }
            } else {
                register(login, session);
            }
        }
    }

    public Map<String, Boolean> containsList(List<String> logins) {
        synchronized (lock) {
            Map<String, Boolean> res = new HashMap<String, Boolean>(logins.size());
            for (String lo : logins) {
                res.put(lo, authorMap.containsKey(lo));
            }
            return res;
        }
    }

    public boolean contains(String login) {
        synchronized (lock) {
            return authorMap.containsKey(login);
        }
    }

    public HttpSession get(String login) {
        synchronized (lock) {
            return authorMap.get(login);
        }
    }

    public void remove(String login) {
        synchronized (lock) {
            unregister(login);
        }
    }

    public void remove(HttpSession session) {
        synchronized (lock) {
            unregister(session);
        }
    }

    public String dump() {
        synchronized (lock) {
            return new ToStringBuilder(this).
                    append("authorMap", authorMap).
                    append("idMap", idMap).
                    toString();
        }
    }

    private void register(String login, HttpSession session) {
        HttpSession existedSession = authorMap.put(login, session);
        String existedLogin = idMap.put(session.getId(), login);
        if(null != existedSession || null != existedLogin) {
            String authorMapDump = authorMap.toString();
            String idMapDump = idMap.toString();
            unregister(login);
            unregister(session);
            throw new IllegalStateException("Preventing registry state corruption," +
                    " authorMap: " + authorMapDump + ", idMap: " + idMapDump +
                    ", login: " + login + ", session: " + session + ", sessionId" + session.getId());
        }
    }

    private void unregister(String login) {
        HttpSession session = authorMap.remove(login);
        if(null != session) {
            String sameLogin = idMap.remove(session.getId());
            // cannot happen
            if(!login.equals(sameLogin)) throw new IllegalStateException("Session registry state corrupted," +
                    " authorMap: " + authorMap + ", idMap: " + idMap);
        }
    }

    private void unregister(HttpSession session) {
        String login = idMap.remove(session.getId());
        if(null != login) {
            HttpSession sameSession = authorMap.remove(login);
            // cannot happen
            if(!session.equals(sameSession)) throw new IllegalStateException("Session registry state corrupted," +
                    " authorMap: " + authorMap + ", idMap: " + idMap);
        }
    }

    public class LockBoundedManager {
        LockBoundedManager() {
        }

        public void put(String login, HttpSession session) {
            register(login, session);
        }

        public void remove(String login) {
            unregister(login);
        }

        public void remove(HttpSession session) {
            unregister(session);
        }
    }
}
