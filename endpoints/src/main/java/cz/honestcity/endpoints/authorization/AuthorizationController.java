package cz.honestcity.endpoints.authorization;

import cz.honestcity.endpoints.configuration.authorization.AuthenticationService;
import cz.honestcity.endpoints.configuration.authorization.JwtTokenService;
import cz.honestcity.model.user.User;
import cz.honestcity.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author michal.keder
 */
@RestController
public class AuthorizationController {

    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationService authenticationService;

    public AuthorizationController( UserService userService, JwtTokenService jwtTokenService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(AuthorizationUrl.REGISTER)
    public PostRegisterResponse register(@RequestBody PostRegisterRequest requestData, HttpServletRequest request){
        User newUser = getNewUser(requestData);
        setSecurityContext(request, newUser);
        return new PostRegisterResponse()
                .setUser(newUser)
                .setAccessToken(getAccessToken(newUser));
    }

    @PostMapping(AuthorizationUrl.LOGIN)
    public PostLoginResponse login(@RequestBody PostLoginRequest requestData, HttpServletRequest request){
        User loggedUser = getLoggedUser(requestData);
        setSecurityContext(request, loggedUser);
        return new PostLoginResponse()
                .setUser(loggedUser)
                .setAccessToken(getAccessToken(loggedUser));
    }

    private void setSecurityContext(HttpServletRequest request, User newUser) {
        authenticationService.setSecurityContext(newUser, request);
    }

    private String getAccessToken(User user){
        return jwtTokenService.getToken(user.getId());
    }

    private User getNewUser(PostRegisterRequest request) {
        return userService.register(request.getLoginData());
    }

    private User getLoggedUser(PostLoginRequest request) {
        return userService.login(request.getUser());
    }
}
