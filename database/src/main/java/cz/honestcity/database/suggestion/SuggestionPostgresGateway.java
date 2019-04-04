package cz.honestcity.database.suggestion;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.gateway.SuggestionGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionPostgresGateway implements SuggestionGateway {

    @Autowired
    private SuggestionPostgresMapper suggestionPostgresMapper;

    @Override
    public void remove(long suggestionId) {
        suggestionPostgresMapper.remove(suggestionId);
    }

    @Override
    public List<Suggestion> getUserSuggestions(long userId) {
        return suggestionPostgresMapper.getUserSuggestions(userId);
    }

    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return suggestionPostgresMapper.getSuggestion(suggestionId);
    }

    @Override
    public List<Suggestion> getExchangePointSuggestions(long exchangePointId) {
        return suggestionPostgresMapper.getExchangePointSuggestions(exchangePointId);
    }

    @Override
    public void reportNonExistingPoint(long exchangePointId, Suggestion suggestion) {
        suggestionPostgresMapper.reportNonExistingPoint(exchangePointId,suggestion);
    }

    @Override
    public void suggestsNewExchangePoint(NewExchangePointSuggestion suggestion) {
        suggestionPostgresMapper.suggestsNewExchangePoint(suggestion);
    }

    @Override
    public void suggestsExchangeRateChange(ExchangeRateSuggestion suggestion) {
        suggestionPostgresMapper.suggestsExchangeRateChange(suggestion);
    }

    @Override
    public void suggestsExchangeRateChange(Suggestion suggestion, long exchangePointId, ExchangeRate suggestedExchangeRate) {

    }

    @Override
    public void suggestsNewExchangePoint(Suggestion suggestion, Position position) {

    }
}
