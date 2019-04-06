package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(SuggestionServiceType.SuggestionServiceTypeNames.EXCHANGE_RATE_CHANGE)
public class ExchangeRateSuggestionService extends SuggestionService{

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
        suggestionGateway.suggestsExchangeRateChange((List<ExchangeRateSuggestion>)suggestions);
    }

    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return suggestionGateway.getExchangeRateSuggestion(suggestionId);
    }

    public List<ExchangeRateSuggestion> getScoredSuggestions(long exchangePointId){
        return suggestionGateway.getExchangePointSuggestions(exchangePointId).stream()
                .sorted(this::compareUserScore)
                .collect(Collectors.toList());
    }

    private int compareUserScore(Suggestion userSuggestion1,Suggestion userSuggestion2){
        return Integer.compare(userSuggestion1.getSuggestedBy().getScore(),userSuggestion2.getSuggestedBy().getScore());
    }
}
