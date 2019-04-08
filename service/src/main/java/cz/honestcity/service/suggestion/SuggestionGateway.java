package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;

import java.util.List;

public interface SuggestionGateway {

    void removeSuggestions(List<? extends Suggestion> toRemove);

}
