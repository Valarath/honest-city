package cz.honestcity.service.suggestion.exchange.rate;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.suggestion.BaseSuggestionService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(SuggestionServiceType.SuggestionServiceTypeNames.EXCHANGE_RATE_CHANGE)
public class ExchangeRateSuggestionService extends BaseSuggestionService {

    @Autowired
    private ExchangeRateSuggestionGateway gateway;

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
        gateway.suggestsExchangeRateChange((List<ExchangeRateSuggestion>)suggestions);
    }

    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return gateway.getExchangeRateSuggestion(suggestionId);
    }

    public List<ExchangeRateSuggestion> getScoredSuggestions(long exchangePointId){
        return gateway.getExchangePointSuggestions(exchangePointId).stream()
                .sorted(this::compareUserScore)
                .collect(Collectors.toList());
    }

    private int compareUserScore(Suggestion userSuggestion1,Suggestion userSuggestion2){
        return Integer.compare(userSuggestion1.getSuggestedBy().getScore(),userSuggestion2.getSuggestedBy().getScore());
    }

    @Override
    public List<? extends Suggestion> getUserSuggestions(long userId) {
        return gateway.getUserSuggestions(userId);
    }
}
