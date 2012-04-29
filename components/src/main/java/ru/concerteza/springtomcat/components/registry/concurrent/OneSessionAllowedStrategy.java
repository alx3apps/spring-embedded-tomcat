package ru.concerteza.springtomcat.components.registry.concurrent;

import javax.servlet.http.HttpSession;

import static org.apache.commons.lang.StringUtils.defaultString;
import static ru.concerteza.springtomcat.components.registry.concurrent.RemoteIpFilter.REMOTE_IP_ATTRIBUTE;

/**
 * User: alexey
 * Date: 4/29/12
 */
public class OneSessionAllowedStrategy implements ConcurrentSessionStrategy {
    @Override
    public void onExisted(HttpSession existed, HttpSession current) {
        String remoteIp = (String) existed.getAttribute(REMOTE_IP_ATTRIBUTE);
        throw new ConcurrentSessionException(defaultString(remoteIp, "UNKNOWN"));
    }
}
