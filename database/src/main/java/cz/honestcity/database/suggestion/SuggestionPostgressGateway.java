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
public class SuggestionPostgressGateway implements SuggestionGateway {

    @Autowired
    private SuggestionPostgressMapper suggestionPostgressMapper;

    @Override
    public void remove(long suggestionId) {
        suggestionPostgressMapper.remove(suggestionId);
    }

    @Override
    public List<Suggestion> getUserSuggestions(long userId) {
        return suggestionPostgressMapper.getUserSuggestions(userId);
    }

    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return suggestionPostgressMapper.getSuggestion(suggestionId);
    }

    @Override
    public List<Suggestion> getExchangePointSuggestions(long exchangePointId) {
        return suggestionPostgressMapper.getExchangePointSuggestions(exchangePointId);
    }

    @Override
    public void reportNonExistingPoint(long exchangePointId, Suggestion suggestion) {
        suggestionPostgressMapper.reportNonExistingPoint(exchangePointId,suggestion);
    }

    @Override
    public void suggestsNewExchangePoint(NewExchangePointSuggestion suggestion) {
        suggestionPostgressMapper.suggestsNewExchangePoint(suggestion);
    }

    @Override
    public void suggestsExchangeRateChange(ExchangeRateSuggestion suggestion) {
        suggestionPostgressMapper.suggestsExchangeRateChange(suggestion);
    }

    @Override
    public void suggestsExchangeRateChange(Suggestion suggestion, long exchangePointId, ExchangeRate suggestedExchangeRate) {

    }

    @Override
    public void suggestsNewExchangePoint(Suggestion suggestion, Position position) {

    }
}
