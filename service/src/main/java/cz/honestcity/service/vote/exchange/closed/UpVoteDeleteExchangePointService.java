package cz.honestcity.service.vote.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.vote.VoteForExchangePointDelete;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.vote.exchange.VoteExchangeService;

//@Service(VoteType.VoteConstants.DELETE_EXCHANGE_POINT)
@HonestCityService(beanId = VoteForExchangePointDelete.class)
public class UpVoteDeleteExchangePointService extends VoteExchangeService {

    public void upVote(long suggestionId, long userId) {
        if (isSuggestionAcceptable(suggestionId, userId))
            acceptDeleteExchangePoint(suggestionId);
        recordVote(suggestionId, userId);
    }

    private void acceptDeleteExchangePoint(long suggestionId) {
        ClosedExchangePointSuggestion suggestion = (ClosedExchangePointSuggestion) suggestionServices.get(SuggestionServiceType.CLOSED_EXCHANGE_POINT.name()).getSuggestion(suggestionId);
        exchangeService.deleteExchangePoint(suggestion.getExchangePointId());
        increaseSuggesterScore(suggestion.getSuggestedBy());
    }
}
