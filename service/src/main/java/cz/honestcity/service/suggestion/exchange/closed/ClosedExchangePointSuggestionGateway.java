package cz.honestcity.service.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.service.suggestion.SuggestionGateway;

import java.util.List;

public interface ClosedExchangePointSuggestionGateway extends SuggestionGateway<ClosedExchangePointSuggestion> {

    List<ClosedExchangePointSuggestion> getExchangePointSuggestions(String exchangePointId);

}
