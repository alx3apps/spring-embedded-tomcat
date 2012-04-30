package ru.concerteza.springtomcat.components.registry.concurrent;

import ru.concerteza.springtomcat.components.registry.SessionRegistry;

import javax.servlet.http.HttpSession;

import static org.apache.commons.lang.StringUtils.defaultString;
import static ru.concerteza.springtomcat.components.registry.concurrent.RemoteIpFilter.REMOTE_IP_ATTRIBUTE;

/**
 * User: alexey
 * Date: 4/29/12
 */
public class OneSessionAllowedStrategy implements ConcurrentSessionStrategy {
    @Override
    public void onExisted(SessionRegistry.LockBoundedManager manager, String login, HttpSession existed, HttpSession current) throws ConcurrentSessionException {
        String existedIp = (String) existed.getAttribute(REMOTE_IP_ATTRIBUTE);
        throw new ConcurrentSessionException(defaultString(existedIp, "UNKNOWN"));
    }
}
