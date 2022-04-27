package cz.honestcity.endpoints.configuration.authorization;

import cz.honestcity.model.user.User;
import cz.honestcity.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author michal.keder
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new JwtUserDetail(userService.getUserByUsername(username));
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        return new JwtUserDetail(userService.getUser(id));
    }

    public UserDetails getByUser(User user) throws UsernameNotFoundException {
        return new JwtUserDetail(user);
    }
}
