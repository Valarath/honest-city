package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.*;
import cz.honestcity.service.gateway.SuggestionGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionService {

    @Autowired
    private SuggestionGateway suggestionGateway;

    public List<ExchangeRateSuggestion> getScoredSuggestions(long exchangePointId){
        return suggestionGateway.getExchangePointSuggestions(exchangePointId).stream()
                .sorted(this::compareUserScore)
                .collect(Collectors.toList());
    }

    private int compareUserScore(Suggestion userSuggestion1,Suggestion userSuggestion2){
        return Integer.compare(userSuggestion1.getSuggestedBy().getScore(),userSuggestion2.getSuggestedBy().getScore());
    }

    public void removeSuggestions(List<? extends Suggestion> toRemove){
        suggestionGateway.removeSuggestions(toRemove);
    }

    public void reportNonExistingPoint(List<NonExistingExchangePointSuggestion> nonExistingExchangePointSuggestions) {
        suggestionGateway.reportNonExistingPoint(nonExistingExchangePointSuggestions);
    }

    public void suggestsExchangeRateChange(List<ExchangeRateSuggestion> exchangeRateSuggestions) {
        suggestionGateway.suggestsExchangeRateChange(exchangeRateSuggestions);
    }

    public void suggestsNewExchangePoint(List<NewExchangePointSuggestion> newExchangePointSuggestions) {
        suggestionGateway.suggestsNewExchangePoint(newExchangePointSuggestions);
    }

    public ExchangeRateSuggestion getExchangeRateSuggestion(long suggestionId){
        return suggestionGateway.getExchangeRateSuggestion(suggestionId);
    }

    public NonExistingExchangePointSuggestion getNonExistingExchangePointSuggestion(long suggestionId){
        return suggestionGateway.getNonExistingExchangePointSuggestion(suggestionId);
    }

    public NewExchangePointSuggestion getNewExchangePointSuggestion(long suggestionId){
        return suggestionGateway.getNewExchangePointSuggestion(suggestionId);
    }
}
