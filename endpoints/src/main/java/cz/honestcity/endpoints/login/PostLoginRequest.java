package cz.honestcity.endpoints.login;

import cz.honestcity.model.login.LoginData;
import lombok.Data;

/**
 * @author michal.keder
 */
@Data
public class PostLoginRequest {

    private String test;
    private LoginData loginData;

}
