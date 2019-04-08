package cz.honestcity.service.vote.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.vote.VoteService;
import org.springframework.stereotype.Service;

@Service(VoteType.VoteConstants.DELETE_EXCHANGE_POINT)
public class UpVoteDeleteExchangePointService extends VoteService {

    public void upVote(long suggestionId, long userId) {
        if(isSuggestionAcceptable(suggestionId, userId))
            acceptDeleteExchangePoint(suggestionId);
        recordVote(suggestionId,userId);
    }

    private void acceptDeleteExchangePoint(long suggestionId){
        ClosedExchangePointSuggestion suggestion = (ClosedExchangePointSuggestion) suggestionServices.get(SuggestionServiceType.CLOSED_EXCHANGE_POINT.name()).getSuggestion(suggestionId);
        exchangeService.deleteExchangePoint(suggestion.getExchangePointId());
        increaseSuggesterScore(suggestion.getSuggestedBy());
    }
}
