package cz.honestcity.service.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;

import java.util.List;

public interface ClosedExchangePointSuggestionGateway {

    void reportClosedPoint(List<ClosedExchangePointSuggestion> closedExchangePointSuggestions);

    ClosedExchangePointSuggestion getClosedExchangePointSuggestion(long suggestionId);

    List<? extends ClosedExchangePointSuggestion> getUserSuggestions(long userId);
}
