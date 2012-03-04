package ru.concerteza.springtomcat.etomcat6.x509;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * User: alexey
 * Date: 3/4/12
 */

@Service("authUserDetailService")
public class AuthUserDetailService implements AuthenticationUserDetailsService {
    @Override
    public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
        if("etomcat_client".equals(token.getName())) {
            return new User("etomcat_client", "", true, true, true, true, Arrays.asList(new Role("ROLE_ETOMCAT_CLIENT")));
        }
        throw new UsernameNotFoundException(token.getName());
    }

    private class Role implements GrantedAuthority {
        private static final long serialVersionUID = 2191234214967125385L;
        private final String authority;

        private Role(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return authority;
        }
    }
}
