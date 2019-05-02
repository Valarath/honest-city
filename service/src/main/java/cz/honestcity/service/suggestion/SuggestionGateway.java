package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;

import java.util.List;

public interface SuggestionGateway {

    void suggests(List<? extends Suggestion> suggestions);

    Suggestion getSuggestion(long suggestionId);

    List<? extends Suggestion> getUserSuggestions(long userId);

}
