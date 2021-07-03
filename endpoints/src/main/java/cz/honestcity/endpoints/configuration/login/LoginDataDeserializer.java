package cz.honestcity.endpoints.configuration.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.honestcity.model.login.LoginData;

/**
 * @author michal.keder
 */
public class LoginDataDeserializer extends DataDeserializer<LoginData> {

    public LoginDataDeserializer(ObjectMapper objectMapper) {
        super(objectMapper, LoginData.class);
    }

    public LoginDataDeserializer(ObjectMapper objectMapper, String packagePath) {
        super(objectMapper, LoginData.class,packagePath);
    }

}
