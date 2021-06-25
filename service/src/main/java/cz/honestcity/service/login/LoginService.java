package cz.honestcity.service.login;

import cz.honestcity.model.login.LoginData;
import cz.honestcity.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author michal.keder
 */

@Service
public class LoginService {

    private final Map<String,LoginGateway> loginGateways;

    public LoginService(Map<String, LoginGateway> loginGateways) {
        this.loginGateways = loginGateways;
    }

    public User login(LoginData loginData){
        return loginGateways.get(loginData.getClass().getSimpleName()).getUser(loginData);
    }
}
