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
        String existedIp = (String) existed.getAttribute(REMOTE_IP_ATTRIBUTE);
        String currentIp = (String) current.getAttribute(REMOTE_IP_ATTRIBUTE);
        if(!existedIp.equals(currentIp)) throw new ConcurrentSessionException(defaultString(existedIp, "UNKNOWN"));
    }
}
