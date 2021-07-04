package cz.honestcity.endpoints.configuration.deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.honestcity.model.suggestion.Suggestion;

/**
 * @author michal.keder
 */
public class SuggestionDeserializer extends DataDeserializer<Suggestion> {

    public SuggestionDeserializer(ObjectMapper objectMapper) {
        super(objectMapper, Suggestion.class);
    }

    public SuggestionDeserializer(ObjectMapper objectMapper, String packagePath) {
        super(objectMapper, Suggestion.class, packagePath);
    }
}
