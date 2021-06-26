package cz.honestcity.endpoints.authorization;

import cz.honestcity.endpoints.UrlPrefix;

/**
 * @author michal.keder
 */
public class AuthorizationUrl {

    private static final String AUTHORIZATION_PREFIX = UrlPrefix.PUBLIC+"/authorization";
    public static final String REGISTER = AUTHORIZATION_PREFIX+"/register";
    public static final String LOGIN = AUTHORIZATION_PREFIX+"/login";
}
