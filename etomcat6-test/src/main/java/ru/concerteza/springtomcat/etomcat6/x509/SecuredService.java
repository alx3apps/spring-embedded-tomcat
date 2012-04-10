package ru.concerteza.springtomcat.etomcat6.x509;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;

/**
 * User: alexey
 * Date: 3/4/12
 */

public interface SecuredService {
    public static final String GREETING = "Hello ETomcat Client!";

    String hello();

    String fail();
}

@Service
class SecuredServiceImpl implements SecuredService {

    @RolesAllowed("ROLE_ETOMCAT_CLIENT")
    public String hello() {
        return GREETING;
    }

    @RolesAllowed("ROLE_FAIL")
    public String fail() {
        return "Access failed";
    }
}
