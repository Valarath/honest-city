package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;

import java.util.List;

@Data
public class PostNewExchangePointRequest {
    private List<NewExchangePointSuggestion> newExchangePointSuggestions;
}
