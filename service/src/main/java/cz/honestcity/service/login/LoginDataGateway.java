package cz.honestcity.service.login;

import cz.honestcity.model.login.LoginData;

/**
 * @author michal.keder
 */
public interface LoginDataGateway<LOGIN_DATA extends LoginData> {

    LOGIN_DATA get(String userId);

    void save(LOGIN_DATA loginData);

}
