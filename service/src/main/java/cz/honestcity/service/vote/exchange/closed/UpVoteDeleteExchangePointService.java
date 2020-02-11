package cz.honestcity.service.vote.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.VoteForExchangePointDelete;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.vote.exchange.VoteExchangeService;

//@Service(VoteType.VoteConstants.DELETE_EXCHANGE_POINT)
@HonestCityService(beanId = VoteForExchangePointDelete.class)
public class UpVoteDeleteExchangePointService extends VoteExchangeService {

    public UpVoteDeleteExchangePointService() {
    }

    public void upVote(Suggestion suggestion, long userId) {
        if (isSuggestionAcceptable(suggestion.getId(), userId))
            acceptDeleteExchangePoint((ClosedExchangePointSuggestion) suggestion);
        recordVote(suggestion.getId(), userId);
    }

    private void acceptDeleteExchangePoint(ClosedExchangePointSuggestion suggestion) {
        exchangeService.deleteExchangePoint(suggestion.getExchangePointId());
        increaseSuggesterScore(suggestion.getSuggestedBy());
    }
}
