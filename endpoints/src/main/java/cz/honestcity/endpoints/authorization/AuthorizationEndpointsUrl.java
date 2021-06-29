package cz.honestcity.endpoints.authorization;

import cz.honestcity.endpoints.EndpointsUrl;

/**
 * @author michal.keder
 */
public class AuthorizationEndpointsUrl {

    private static final String AUTHORIZATION_PREFIX = EndpointsUrl.PUBLIC+"/authorization";
    public static final String REGISTER = AUTHORIZATION_PREFIX+"/register";
    public static final String LOGIN = AUTHORIZATION_PREFIX+"/login";
}
