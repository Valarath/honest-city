package cz.honestcity.endpoints.configuration.authorization;

import cz.honestcity.model.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author michal.keder
 */
@Service
public class AuthenticationService {

    private final JwtUserDetailsService userDetailsService;

    public AuthenticationService(JwtUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setSecurityContext(String userId, HttpServletRequest request) {
        Authentication authentication = getAuthentication(getUserDetails(userId), request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void setSecurityContext(User user, HttpServletRequest request) {
        Authentication authentication = getAuthentication(getUserDetails(user), request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails getUserDetails(String userId) {
        return userDetailsService.loadUserById(userId);
    }

    private UserDetails getUserDetails(User user) {
        return userDetailsService.getByUser(user);
    }

    private Authentication getAuthentication(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }
}
