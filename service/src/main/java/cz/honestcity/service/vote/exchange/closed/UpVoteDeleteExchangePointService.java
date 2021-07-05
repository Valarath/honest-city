package cz.honestcity.service.vote.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.vote.VoteForExchangePointDelete;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.vote.VoteService;

@HonestCityService(beanId = VoteForExchangePointDelete.class)
public class UpVoteDeleteExchangePointService extends VoteService<VoteForExchangePointDelete, ClosedExchangePointSuggestion> {

    public UpVoteDeleteExchangePointService() {
    }

    public void upVote(ClosedExchangePointSuggestion suggestion, String userId) {
        ClosedExchangePointSuggestion persistedSuggestion = getSuggestion(suggestion);
        if (persistedSuggestion != null)
            performVote(persistedSuggestion, userId);
    }

    public void performVote(ClosedExchangePointSuggestion suggestion, String userId) {
        if (isSuggestionAcceptable(suggestion, userId))
            acceptDeleteExchangePoint(suggestion);
        recordVote(suggestion, userId);
    }

    private void acceptDeleteExchangePoint(ClosedExchangePointSuggestion suggestion) {
        exchangeService.deleteExchangePoint(suggestion.getSubjectId());
        increaseSuggesterScore(suggestion.getSuggestedBy());
        updateSuggestion(suggestion);
    }
}
