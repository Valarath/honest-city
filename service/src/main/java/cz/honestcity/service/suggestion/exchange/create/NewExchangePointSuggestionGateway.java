package cz.honestcity.service.suggestion.exchange.create;

import cz.honestcity.model.suggestion.NewExchangePointSuggestion;

import java.util.List;

public interface NewExchangePointSuggestionGateway {

    void suggestsNewExchangePoint(List<NewExchangePointSuggestion> suggestions);

    NewExchangePointSuggestion getNewExchangePointSuggestion(long suggestionId);
}
