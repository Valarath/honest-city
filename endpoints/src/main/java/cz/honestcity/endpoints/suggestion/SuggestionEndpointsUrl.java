package cz.honestcity.endpoints.suggestion;

import cz.honestcity.endpoints.EndpointsUrl;

/**
 * @author michal.keder
 */
public class SuggestionEndpointsUrl {

    private static final String SUGGESTION_PREFIX = EndpointsUrl.PRIVATE+"/suggestion";
    public static final String SUGGEST = SUGGESTION_PREFIX+"/suggest";
    public static final String REMOVE = SUGGESTION_PREFIX+"/remove";
}
