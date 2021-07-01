package cz.honestcity.endpoints.user;

import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class GetUserSuggestionsResponse {
    private Map<String,List<? extends Suggestion>> userSuggestions;
}
