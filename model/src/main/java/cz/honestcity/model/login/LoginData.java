package cz.honestcity.model.login;

import cz.honestcity.model.HonestCitySerializable;

/**
 * @author michal.keder
 */
public abstract class LoginData extends HonestCitySerializable {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public LoginData setUserId(String userId) {
        this.userId = userId;
        return this;
    }

}
