package cz.honestcity.endpoints.configuration.login;

import cz.honestcity.model.suggestion.Suggestion;

/**
 * @author michal.keder
 */
public class SuggestionDeserializer extends DataDeserializer<Suggestion> {

    public SuggestionDeserializer() {
        super(Suggestion.class);
    }

    public SuggestionDeserializer(String packagePath) {
        super(Suggestion.class, packagePath);
    }
}
