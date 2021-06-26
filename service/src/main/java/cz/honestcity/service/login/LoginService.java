package cz.honestcity.service.login;

import cz.honestcity.model.login.LoginData;
import cz.honestcity.model.user.User;
import cz.honestcity.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author michal.keder
 */

@Service
public class LoginService {

    private final Map<String,LoginGateway> loginGateways;
    private final UserService userService;

    public LoginService(Map<String, LoginGateway> loginGateways, UserService userService) {
        this.loginGateways = loginGateways;
        this.userService = userService;
    }

    public User register(LoginData loginData){
        User user = getLoginGateway(loginData).getUser(loginData);
        userService.saveNewUser(user);
        return user;
    }

    public User register(User user){
        User updatedUserData = getLoginGateway(user.getLoginData()).getUser(user.getLoginData());
        User updatedUser = updateUser(user, updatedUserData);
        userService.updateUserData(updatedUser);
        return updatedUser;
    }

    private User updateUser(User actualUser, User updatedUser){
        return actualUser
                .setEmail(updatedUser.getEmail())
                .setUsername(updatedUser.getUsername());
    }

    private LoginGateway<? super LoginData> getLoginGateway(LoginData loginData) {
        return loginGateways.get(loginData.getClass().getSimpleName());
    }
}
