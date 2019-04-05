package cz.honestcity.database.suggestion;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
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
    public List<ExchangeRateSuggestion> getExchangePointSuggestions(long exchangePointId) {
        return suggestionPostgresMapper.getExchangePointSuggestions(exchangePointId);
    }

    @Override
    public void reportNonExistingPoint(List<NonExistingExchangePointSuggestion> nonExistingExchangePointSuggestions) {
        suggestionPostgresMapper.reportNonExistingPoint(nonExistingExchangePointSuggestions);
    }

    @Override
    public void suggestsNewExchangePoint(List<NewExchangePointSuggestion> suggestions) {
        suggestionPostgresMapper.suggestsNewExchangePoint(suggestions);
    }

    @Override
    public void suggestsExchangeRateChange(List<ExchangeRateSuggestion> suggestions) {
        suggestionPostgresMapper.suggestsExchangeRateChange(suggestions);
    }

    @Override
    public void removeSuggestions(List<? extends Suggestion> toRemove) {
        suggestionPostgresMapper.removeSuggestions(toRemove);
    }

    @Override
    public ExchangeRateSuggestion getExchangeRateSuggestion(long suggestionId) {
        return null;
    }

    @Override
    public NonExistingExchangePointSuggestion getNonExistingExchangePointSuggestion(long suggestionId) {
        return null;
    }

    @Override
    public NewExchangePointSuggestion getNewExchangePointSuggestion(long suggestionId) {
        return null;
    }

}
