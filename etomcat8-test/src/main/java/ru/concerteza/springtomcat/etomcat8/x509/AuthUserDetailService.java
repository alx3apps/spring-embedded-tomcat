package ru.concerteza.springtomcat.etomcat8.x509;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * User: alexey
 * Date: 3/4/12
 */

@Service("authUserDetailsService")
public class AuthUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        if("etomcat_client".equals(username)) {
            return new User("etomcat_client", "", true, true, true, true, Arrays.asList(new Role("ROLE_ETOMCAT_CLIENT")));
        }
        throw new UsernameNotFoundException(username);
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
