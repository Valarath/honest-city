package cz.honestcity.service.vote.exchange.create;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.VoteForNewExchangePoint;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.vote.exchange.VoteExchangeService;

//@Service(VoteType.VoteConstants.NEW_EXCHANGE_POINT)
@HonestCityService(beanId = VoteForNewExchangePoint.class)
public class UpVoteNewExchangePointService extends VoteExchangeService {


    public UpVoteNewExchangePointService() {
    }

    public void upVote(Suggestion suggestion, long userId) {
        if (isSuggestionAcceptable(suggestion.getId(), userId))
            acceptNewExchangePoint((NewExchangePointSuggestion) suggestion);
        recordVote(suggestion.getId(), userId);
    }

    private void acceptNewExchangePoint(NewExchangePointSuggestion suggestion) {
        exchangeService.createSubject(getNewExchangePoint(suggestion));
        increaseSuggesterScore(suggestion.getSuggestedBy());
    }

    private ExchangePoint getNewExchangePoint(NewExchangePointSuggestion suggestion) {
        return (ExchangePoint) new ExchangePoint()
                .setPosition(suggestion.getPosition())
                .setHonestyStatus(HonestyStatus.UNKNOWN);
    }
}
