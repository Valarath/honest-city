package cz.honestcity.service.gateway;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;

import java.util.List;

public interface SuggestionGateway {

    List<ExchangeRateSuggestion> getExchangePointSuggestions(long exchangePointId);

    void reportNonExistingPoint(List<NonExistingExchangePointSuggestion> nonExistingExchangePointSuggestions);

    void suggestsNewExchangePoint(List<NewExchangePointSuggestion> suggestions);

    void suggestsExchangeRateChange(List<ExchangeRateSuggestion> suggestions);

    void removeSuggestions(List<? extends Suggestion> toRemove);
}
