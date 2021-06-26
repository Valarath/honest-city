package cz.honestcity.endpoints.authorization;

import cz.honestcity.model.user.User;
import cz.honestcity.service.login.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michal.keder
 */
@RestController
public class AuthorizationController {

    private final LoginService loginService;

    public AuthorizationController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(AuthorizationUrl.REGISTER)
    public PostRegisterResponse register(@RequestBody PostRegisterRequest request){
        return new PostRegisterResponse()
                .setUser(getNewUser(request));
    }

    @PostMapping(AuthorizationUrl.LOGIN)
    public PostLoginResponse login(@RequestBody PostLoginRequest request){
        return new PostLoginResponse()
                .setUser(getLoggedUser(request));
    }

    private User getNewUser(PostRegisterRequest request) {
        return loginService.register(request.getLoginData());
    }

    private User getLoggedUser(PostLoginRequest request) {
        return loginService.register(request.getUser());
    }
}
