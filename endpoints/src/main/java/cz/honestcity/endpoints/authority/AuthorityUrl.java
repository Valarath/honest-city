package cz.honestcity.endpoints.authority;

import cz.honestcity.endpoints.UrlPrefix;

/**
 * @author michal.keder
 */
public class AuthorityUrl {

    private static final String AUTHORITY_PREFIX = UrlPrefix.PUBLIC+"/authority";
    public static final String GET_RATE = AUTHORITY_PREFIX+"/rate";
}
