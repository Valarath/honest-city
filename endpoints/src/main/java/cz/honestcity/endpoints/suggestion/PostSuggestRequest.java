package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;

import java.util.List;

@Data
public class PostSuggestRequest {
    private List<? extends Suggestion> newExchangePointSuggestions;

}
