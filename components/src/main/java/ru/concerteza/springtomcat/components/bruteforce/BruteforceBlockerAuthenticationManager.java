package ru.concerteza.springtomcat.components.bruteforce;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.System.currentTimeMillis;

/**
 * User: alexey
 * Date: 12/16/11
 */
public class BruteforceBlockerAuthenticationManager implements AuthenticationManager {
    private AuthenticationManager authenticationManager;
    private int maxAttempts;
    private long cooldownPeriod;
    private TimeUnit cooldownPeriodUnit;

    // concurrent access won't break anything serious in this case, so using CHM instead of locking
    private final ConcurrentHashMap<String, Entry> registry = new ConcurrentHashMap<String, Entry>();

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public void setCooldownPeriod(long cooldownPeriod) {
        this.cooldownPeriod = cooldownPeriod;
    }

    public void setCooldownPeriodUnit(TimeUnit cooldownPeriodUnit) {
        this.cooldownPeriodUnit = cooldownPeriodUnit;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // it may be some SpringSecurity special cases, we wouldn't interfere
        if(null == authentication || null == authentication.getName()) return authenticationManager.authenticate(authentication);
        String name = authentication.getName();
        checkBruteforce(name);
        Authentication res = authenticationManager.authenticate(authentication);
        // got here means success auth
        registry.remove(name);
        return res;
    }

    private void checkBruteforce(String name) throws BruteforceSuspectedException {
        sweep();
        Entry en = registry.get(name);
        // first attempt
        if(null == en) {
            registry.put(name, new Entry());
            return;
        }
        // following attempts
        long now = currentTimeMillis();
        long period = TimeUnit.MILLISECONDS.convert(cooldownPeriod, cooldownPeriodUnit);
        // cooldown passed
        if (now - en.getLastAttemptMoment() > period) {
            registry.put(name, new Entry());
            return;
        }
        // register current attempt
        en.increment();
        // threshold not crossed
        int currentAttempts = en.getAttemptsCount();
        if(currentAttempts < maxAttempts) return;
        // guards, take him away!
        throw new BruteforceSuspectedException(name, currentAttempts);
    }

    // prevent memory leaks caused by many different login attempts
    private void sweep() {
        long now = currentTimeMillis();
        long period = TimeUnit.MILLISECONDS.convert(cooldownPeriod, cooldownPeriodUnit);
        Set<Map.Entry<String, Entry>> set = registry.entrySet();
        for (Map.Entry<String, Entry> en : set) {
            if (now - en.getValue().getLastAttemptMoment() > period) {
                set.remove(en);
            }
        }
    }

    private class Entry {
        private final AtomicInteger attemptsCount = new AtomicInteger(0);
        private final AtomicLong lastAttemptMoment = new AtomicLong(0);

        private Entry() {
            attemptsCount.incrementAndGet();
            lastAttemptMoment.set(currentTimeMillis());
        }

        public int getAttemptsCount() {
            return attemptsCount.get();
        }

        public long getLastAttemptMoment() {
            return lastAttemptMoment.get();
        }

        public void increment() {
            attemptsCount.incrementAndGet();
            lastAttemptMoment.set(currentTimeMillis());
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).
                    append("attemptsCount", attemptsCount).
                    append("lastAttemptMoment", lastAttemptMoment).
                    toString();
        }
    }
}
