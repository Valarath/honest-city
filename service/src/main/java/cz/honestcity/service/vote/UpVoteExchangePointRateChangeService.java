package cz.honestcity.service.vote;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.stereotype.Service;

@Service(VoteType.VoteConstants.EXCHANGE_RATE_CHANGE)
public class UpVoteExchangePointRateChangeService extends VoteService{

    public void upVote(long suggestionId, long userId) {
        if(isSuggestionAcceptable(suggestionId, userId))
            acceptExchangeRateChange(suggestionId);
        recordVote(suggestionId,userId);
    }

    private void acceptExchangeRateChange(long suggestionId){
        ExchangeRateSuggestion suggestion = (ExchangeRateSuggestion) suggestionServices.get(SuggestionServiceType.EXCHANGE_RATE_CHANGE.name()).getSuggestion(suggestionId);
        exchangeService.changeExchangeRate(suggestion.getSuggestedExchangeRate().getId(),suggestion.getExchangePointId());
        increaseSuggesterScore(suggestion.getSuggestedBy());
    }
}
