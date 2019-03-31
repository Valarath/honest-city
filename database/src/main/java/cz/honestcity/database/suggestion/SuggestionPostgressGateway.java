package cz.honestcity.database.suggestion;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.gateway.SuggestionGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionPostgressGateway implements SuggestionGateway {
    @Override
    public void remove(long suggestionId) {

    }

    @Override
    public List<Suggestion> getUserSuggestions(long userId) {
        return null;
    }

    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return null;
    }

    @Override
    public List<Suggestion> getExchangePointSuggestions(long exchangePointId) {
        return null;
    }

    @Override
    public void reportNonExistingPoint(long exchangePointId, Suggestion suggestion) {

    }

    @Override
    public void suggestsNewExchangePoint(NewExchangePointSuggestion suggestion) {

    }

    @Override
    public void suggestsExchangeRateChange(ExchangeRateSuggestion suggestion) {

    }

    @Override
    public void suggestsExchangeRateChange(Suggestion suggestion, long exchangePointId, ExchangeRate suggestedExchangeRate) {

    }

    @Override
    public void suggestsNewExchangePoint(Suggestion suggestion, Position position) {

    }
}
