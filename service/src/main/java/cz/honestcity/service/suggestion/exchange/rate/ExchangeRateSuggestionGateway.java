package cz.honestcity.service.suggestion.exchange.rate;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.Suggestion;

import java.util.List;

public interface ExchangeRateSuggestionGateway {

    List<? extends ExchangeRateSuggestion> getExchangePointSuggestions(long exchangePointId);

    void suggestsExchangeRateChange(List<ExchangeRateSuggestion> suggestions);

    ExchangeRateSuggestion getExchangeRateSuggestion(long suggestionId);
}
