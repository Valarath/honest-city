package cz.honestcity.service.vote.exchange.rate;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.suggestion.exchange.rate.ExchangeRateSuggestionService;
import cz.honestcity.service.vote.exchange.VoteExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service(VoteType.VoteConstants.EXCHANGE_RATE_CHANGE)
public class UpVoteExchangePointRateChangeService extends VoteExchangeService {

    @Autowired
    private ExchangeRateSuggestionService exchangeRateSuggestionService;

    public void upVote(long suggestionId, long userId) {
        if(isSuggestionAcceptable(suggestionId, userId))
            acceptExchangeRateChange(suggestionId);
        recordVote(suggestionId,userId);
    }

    private void acceptExchangeRateChange(long suggestionId){
        ExchangeRateSuggestion suggestion = (ExchangeRateSuggestion) suggestionServices.get(SuggestionServiceType.EXCHANGE_RATE_CHANGE.name()).getSuggestion(suggestionId);
        exchangeService.changeExchangeRate(suggestion.getSuggestedExchangeRate().getId(),suggestion.getExchangePointId());
        removeDeclinedSuggestions(suggestion);
        increaseSuggesterScore(suggestion.getSuggestedBy());
    }

    private void removeDeclinedSuggestions(ExchangeRateSuggestion suggestion){
        List<ExchangeRateSuggestion> scoredSuggestions = getInProgressSuggestions(suggestion);
        scoredSuggestions.remove(suggestion);
        exchangeRateSuggestionService.removeSuggestions(scoredSuggestions);
    }

    private List<ExchangeRateSuggestion> getInProgressSuggestions(ExchangeRateSuggestion suggestion) {
        return exchangeRateSuggestionService.getScoredSuggestions(suggestion.getExchangePointId()).stream()
                .filter(suggestionIsInProgressState())
                .collect(Collectors.toList());
    }

    private Predicate<ExchangeRateSuggestion> suggestionIsInProgressState() {
        return scoredSuggestion ->scoredSuggestion.getState().equals(State.IN_PROGRESS);
    }
}
