package cz.honestcity.endpoints.login;

import cz.honestcity.model.user.User;
import cz.honestcity.service.login.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michal.keder
 */
@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public PostLoginResponse login(@RequestBody PostLoginRequest request){
        return new PostLoginResponse()
                .setUser(getUser(request));
    }

    private User getUser(PostLoginRequest request) {
        return loginService.login(request.getLoginData());
    }
}
