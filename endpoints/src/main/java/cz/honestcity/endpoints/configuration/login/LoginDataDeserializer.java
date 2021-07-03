package cz.honestcity.endpoints.configuration.login;

import cz.honestcity.model.login.LoginData;

/**
 * @author michal.keder
 */
public class LoginDataDeserializer extends DataDeserializer<LoginData> {

    public LoginDataDeserializer() {
        super(LoginData.class);
    }

    public LoginDataDeserializer(String packagePath) {
        super(LoginData.class,packagePath);
    }

}
