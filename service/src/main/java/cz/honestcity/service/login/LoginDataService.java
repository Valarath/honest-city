package cz.honestcity.service.login;

import cz.honestcity.model.login.LoginData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author michal.keder
 */
@Service
public class LoginDataService {

    private final Map<String,LoginDataGateway<? extends LoginData>> loginDataGatewaysByLoginData;

    public LoginDataService(Map<String, LoginDataGateway<? extends LoginData>> loginDataGatewaysByLoginData) {
        this.loginDataGatewaysByLoginData = loginDataGatewaysByLoginData;
    }

    public LoginData get(String userId) {
        return loginDataGatewaysByLoginData.values().stream()
                .map(it -> it.get(userId))
                .findFirst()
                .orElseThrow();
    }

    public String getUserIdIfAlreadyExist(LoginData loginData) {
        return getGatewayByLoginData(loginData).findUserId(loginData);
    }

    public void save(LoginData loginData) {
        getGatewayByLoginData(loginData).save(loginData);
    }

    private <LOGIN_DATA extends LoginData> LoginDataGateway<LOGIN_DATA> getGatewayByLoginData(LOGIN_DATA loginData){
        return (LoginDataGateway<LOGIN_DATA>)loginDataGatewaysByLoginData.get(loginData.getClassName()+LoginDataGateway.class.getSimpleName());
    }

}
