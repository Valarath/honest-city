package cz.honestcity.service.suggestion.exchange.rate;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.suggestion.SuggestionGateway;

import java.util.List;

public interface ExchangeRateSuggestionGateway extends SuggestionGateway {

    List<? extends ExchangeRateSuggestion> getExchangePointSuggestions(String exchangePointId);

}
