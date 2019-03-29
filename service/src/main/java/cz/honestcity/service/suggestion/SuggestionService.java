package cz.honestcity.service.suggestion;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.exchange.ExchangeService;
import cz.honestcity.service.gateway.SuggestionGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionService {

    @Autowired
    private SuggestionGateway suggestionGateway;

    public List<Suggestion> getUserSuggestions(long userId) {
        return suggestionGateway.getUserSuggestions(userId);
    }

    public void remove(long suggestionId) {
        suggestionGateway.remove(suggestionId);
    }

    public List<Suggestion> getExchangePointSuggestions(long exchangePointId){
        return suggestionGateway.getExchangePointSuggestions(exchangePointId);
    }

    public void removeSuggestions(List<Suggestion> toRemove){

    }

    public Suggestion getSuggestion(long suggestionId) {
        return suggestionGateway.getSuggestion(suggestionId);
    }

    public void reportNonExistingPoint(long exchangePointId, Suggestion suggestion) {
        suggestionGateway.reportNonExistingPoint(exchangePointId,suggestion);
    }

    public void suggestsExchangeRateChange(Suggestion suggestion, long exchangePointId, ExchangeRate suggestedExchangeRate) {
        suggestionGateway.suggestsExchangeRateChange(suggestion,exchangePointId,suggestedExchangeRate);
    }

    public void suggestsNewExchangePoint(Suggestion suggestion, Position position) {
        suggestionGateway.suggestsNewExchangePoint(suggestion,position);
    }
}
