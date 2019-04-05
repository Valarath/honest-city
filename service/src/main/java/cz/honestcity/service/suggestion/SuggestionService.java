package cz.honestcity.service.suggestion;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.gateway.SuggestionGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionService {

    @Autowired
    private SuggestionGateway suggestionGateway;

    private List<Suggestion> getScoredSuggestions(long exchangePointId){
        return suggestionGateway.getExchangePointSuggestions(exchangePointId).stream()
                .sorted(this::compareUserScore)
                .collect(Collectors.toList());
    }

    private int compareUserScore(Suggestion userSuggestion1,Suggestion userSuggestion2){
        return Integer.compare(userSuggestion1.getSuggestedBy().getScore(),userSuggestion2.getSuggestedBy().getScore());
    }

    public List<Suggestion> getUserSuggestions(long userId) {
        return suggestionGateway.getUserSuggestions(userId);
    }

    public void remove(long suggestionId) {
        suggestionGateway.remove(suggestionId);
    }

    public void removeSuggestions(List<Suggestion> toRemove){

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
