package cz.honestcity.service.vote.exchange.rate;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.vote.VoteService;
import cz.honestcity.service.vote.exchange.VoteExchangeService;
import org.springframework.stereotype.Service;

@Service(VoteType.VoteConstants.EXCHANGE_RATE_CHANGE)
public class UpVoteExchangePointRateChangeService extends VoteExchangeService {

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
