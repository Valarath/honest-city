package cz.honestcity.service.vote.exchange.rate;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.VoteForExchangePointRateChange;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.exchange.rate.ExchangeRateSuggestionService;
import cz.honestcity.service.vote.VoteService;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//@Service(VoteType.VoteConstants.EXCHANGE_RATE_CHANGE)
@HonestCityService(beanId = VoteForExchangePointRateChange.class)
public class UpVoteExchangePointRateChangeService extends VoteService<VoteForExchangePointRateChange,ExchangeRateSuggestion> {

    private final ExchangeRateSuggestionService exchangeRateSuggestionService;

    public UpVoteExchangePointRateChangeService(ExchangeRateSuggestionService exchangeRateSuggestionService) {
        this.exchangeRateSuggestionService = exchangeRateSuggestionService;
    }

    public void upVote(ExchangeRateSuggestion suggestion, String userId) {
        if (isSuggestionAcceptable(suggestion.getId(), userId))
            acceptExchangeRateChange( suggestion);
        recordVote(suggestion.getId(), userId);
    }

    private void acceptExchangeRateChange(ExchangeRateSuggestion suggestion) {
        exchangeService.changeExchangeRate(suggestion.getSuggestedExchangeRate().getId(), suggestion.getSubjectId());
        removeDeclinedSuggestions(suggestion);
        increaseSuggesterScore(suggestion.getSuggestedBy());
    }

    private void removeDeclinedSuggestions(ExchangeRateSuggestion suggestion){
        List<ExchangeRateSuggestion> scoredSuggestions = getInProgressSuggestions(suggestion);
        scoredSuggestions.remove(suggestion);
        exchangeRateSuggestionService.removeSuggestions(scoredSuggestions);
    }

    private List<ExchangeRateSuggestion> getInProgressSuggestions(ExchangeRateSuggestion suggestion) {
        return exchangeRateSuggestionService.getScoredSuggestions(suggestion.getSubjectId()).stream()
                .filter(suggestionIsInProgressState())
                .collect(Collectors.toList());
    }

    private Predicate<ExchangeRateSuggestion> suggestionIsInProgressState() {
        return scoredSuggestion ->scoredSuggestion.getState().equals(State.IN_PROGRESS);
    }
}
