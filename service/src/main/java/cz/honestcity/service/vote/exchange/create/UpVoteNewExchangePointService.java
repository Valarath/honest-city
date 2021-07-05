package cz.honestcity.service.vote.exchange.create;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.VoteForNewExchangePoint;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.configuration.IdProvider;
import cz.honestcity.service.vote.VoteService;

//@Service(VoteType.VoteConstants.NEW_EXCHANGE_POINT)
@HonestCityService(beanId = VoteForNewExchangePoint.class)
public class UpVoteNewExchangePointService extends VoteService<VoteForNewExchangePoint,NewExchangePointSuggestion> {

    private final IdProvider idProvider;

    public UpVoteNewExchangePointService(IdProvider idProvider) {
        this.idProvider = idProvider;
    }

    public void upVote(NewExchangePointSuggestion suggestion, String userId) {
        if (isSuggestionAcceptable(suggestion.getId(), userId))
            acceptNewExchangePoint( suggestion);
        recordVote(suggestion.getId(), userId);
    }

    private void acceptNewExchangePoint(NewExchangePointSuggestion suggestion) {
        exchangeService.createSubject(getNewExchangePoint(suggestion));
        increaseSuggesterScore(getUser(suggestion));
    }

    private ExchangePoint getNewExchangePoint(NewExchangePointSuggestion suggestion) {
        return (ExchangePoint) new ExchangePoint()
                .setPosition(suggestion.getPosition())
                .setId(idProvider.provideNewId())
                .setHonestyStatus(HonestyStatus.UNKNOWN);
    }
}
