package cz.honestcity.service.suggestion.exchange.create;

import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;

import java.util.List;

public interface NewExchangePointSuggestionGateway {

    void suggestsNewExchangePoint(List<NewExchangePointSuggestion> suggestions);

    NewExchangePointSuggestion getNewExchangePointSuggestion(long suggestionId);

    List<? extends NewExchangePointSuggestion> getUserSuggestions(long userId);
}
