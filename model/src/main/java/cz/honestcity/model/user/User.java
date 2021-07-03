package cz.honestcity.model.user;

import cz.honestcity.model.HonestCitySerializable;
import cz.honestcity.model.login.LoginData;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class User extends HonestCitySerializable {
    private String id;
    private String username;
    private int score;
    private String email;
    private LoginData loginData;
}
