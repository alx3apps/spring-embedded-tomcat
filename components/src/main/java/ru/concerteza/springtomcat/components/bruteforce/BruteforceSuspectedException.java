package ru.concerteza.springtomcat.components.bruteforce;


import org.springframework.security.core.AuthenticationException;

/**
 * User: alexey
 * Date: 12/16/11
 */
public class BruteforceSuspectedException extends AuthenticationException {

    public BruteforceSuspectedException(String login, int attemptsCount) {
        super("Login: '" + login + "', attemptsCount: " + attemptsCount);
    }
}
