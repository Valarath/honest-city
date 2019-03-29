package cz.honestcity.service.gateway;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;

import java.util.List;

public interface SuggestionGateway {
    void remove(long suggestionId);

    List<Suggestion> getUserSuggestions(long userId);

    Suggestion getSuggestion(long suggestionId);

    List<Suggestion> getExchangePointSuggestions(long exchangePointId);

    void reportNonExistingPoint(long exchangePointId, Suggestion suggestion);

    void suggestsNewExchangePoint(NewExchangePointSuggestion suggestion);

    void suggestsExchangeRateChange(ExchangeRateSuggestion suggestion);

    void suggestsExchangeRateChange(Suggestion suggestion, long exchangePointId, ExchangeRate suggestedExchangeRate);

    void suggestsNewExchangePoint(Suggestion suggestion, Position position);
}
