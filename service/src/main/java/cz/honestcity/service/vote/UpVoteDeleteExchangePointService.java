package cz.honestcity.service.vote;

import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.vote.VoteType;
import org.springframework.stereotype.Service;

@Service(VoteType.VoteConstants.DELETE_EXCHANGE_POINT)
public class UpVoteDeleteExchangePointService extends VoteService {

    public void upVote(long suggestionId, long userId) {
        if(isSuggestionAcceptable(suggestionId, userId))
            acceptDeleteExchangePoint(suggestionId);
        recordVote(suggestionId,userId);
    }

    private void acceptDeleteExchangePoint(long suggestionId){
        NonExistingExchangePointSuggestion suggestion =  suggestionService.getNonExistingExchangePointSuggestion(suggestionId);
        exchangeService.deleteExchangePoint(suggestion.getExchangePointId());
        increaseSuggesterScore(suggestion.getSuggestedBy());
    }
}
