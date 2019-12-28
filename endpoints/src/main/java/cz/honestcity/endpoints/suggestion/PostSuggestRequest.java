package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import lombok.Data;

import java.util.List;

@Data
public class PostSuggestRequest {
    private List<? extends Suggestion> newExchangePointSuggestions;
    private SuggestionServiceType suggestionServiceType;

}
