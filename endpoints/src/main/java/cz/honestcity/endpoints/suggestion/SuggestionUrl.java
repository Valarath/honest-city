package cz.honestcity.endpoints.suggestion;

import cz.honestcity.endpoints.UrlPrefix;

/**
 * @author michal.keder
 */
public class SuggestionUrl {

    private static final String SUGGESTION_PREFIX = UrlPrefix.PRIVATE+"/suggestion";
    public static final String SUGGEST = SUGGESTION_PREFIX+"/suggest";
    public static final String REMOVE = SUGGESTION_PREFIX+"/remove";
}
