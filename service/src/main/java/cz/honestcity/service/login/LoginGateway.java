package cz.honestcity.service.login;


import cz.honestcity.model.login.LoginData;
import cz.honestcity.model.user.User;

/**
 * @author michal.keder
 */
public  interface LoginGateway <LOGIN_DATA extends LoginData> {

    User getUser(LOGIN_DATA loginData);

}
