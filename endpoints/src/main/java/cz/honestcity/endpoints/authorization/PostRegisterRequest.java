package cz.honestcity.endpoints.authorization;

import cz.honestcity.model.login.LoginData;
import lombok.Data;

/**
 * @author michal.keder
 */
@Data
public class PostRegisterRequest {

    private LoginData loginData;

}
