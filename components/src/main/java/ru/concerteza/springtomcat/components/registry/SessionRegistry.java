package ru.concerteza.springtomcat.components.registry;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

/**
 * User: alexey
 * Date: 11/4/11
 */
public class SessionRegistry {
    private final Map<String, HttpSession> registry = new HashMap<String, HttpSession>();
    private final Object lock = new Object();

    public void put(String login, HttpSession session) {
        synchronized (lock) {
            HttpSession existed = registry.get(login);
            if(null == existed) {
                registry.put(login, session);
            } else if(!existed.getId().equals(session.getId())) {
                // logging out other user with same login
                // todo logme
                existed.invalidate();
                registry.put(login, session);
            }
        }
    }

    public Map<String, Boolean> containsList(List<String> logins) {
        synchronized (lock) {
            Map<String, Boolean> res = new HashMap<String, Boolean>(logins.size());
            for (String lo : logins) {
                res.put(lo, registry.containsKey(lo));
            }
            return res;
        }
    }

    public boolean contains(String login) {
        synchronized (lock) {
            return registry.containsKey(login);
        }
    }

    public HttpSession get(String login) {
        synchronized (lock) {
            return registry.get(login);
        }
    }

    public void remove(String login) {
        synchronized (lock) {
            registry.remove(login);
        }
    }
}
