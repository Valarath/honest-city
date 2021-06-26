package cz.honestcity.endpoints.configuration.authorization;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author michal.keder
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String JWT_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private final JwtTokenService jwtTokenService;
    private final AuthenticationService authenticationService;

    public JwtAuthenticationTokenFilter(JwtTokenService jwtTokenService, AuthenticationService authenticationService) {
        this.jwtTokenService = jwtTokenService;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userId = getUserId(request.getHeader(JWT_HEADER));
        if (userId != null)
            authenticationService.setSecurityContext(userId,request);
        filterChain.doFilter(request, response);
    }

    private String getUserId(String header) {
        if (header != null && header.startsWith(TOKEN_PREFIX))
            return parseUserId(header);
        return null;
    }

    private String parseUserId(String header) {
        return jwtTokenService.getUserId(header.substring(TOKEN_PREFIX.length()));
    }

}
