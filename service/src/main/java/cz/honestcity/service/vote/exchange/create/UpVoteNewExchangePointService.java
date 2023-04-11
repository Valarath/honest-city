package cz.honestcity.service.vote.exchange.create;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.vote.Vote;
import cz.honestcity.model.vote.VoteForNewExchangePoint;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.configuration.IdProvider;
import cz.honestcity.service.vote.VoteService;

@HonestCityService(beanId = Vote.class, beanIdSpecification = NewExchangePointSuggestion.class)
public class UpVoteNewExchangePointService extends VoteService<Vote,NewExchangePointSuggestion> {

    private final IdProvider idProvider;

    public UpVoteNewExchangePointService(IdProvider idProvider) {
        this.idProvider = idProvider;
    }

    public void upVote(NewExchangePointSuggestion suggestion, String userId) {
        NewExchangePointSuggestion persistedSuggestion = getSuggestion(suggestion);
        if(persistedSuggestion !=null && !voteGateway.isVoteRecorded(persistedSuggestion.getId(),userId))
            performVote(persistedSuggestion,userId);
    }

    private void performVote(NewExchangePointSuggestion suggestion, String userId){
        if (isSuggestionAcceptable(suggestion, userId))
            acceptNewExchangePoint( suggestion);
        recordVote(suggestion, userId);
    }

    private void acceptNewExchangePoint(NewExchangePointSuggestion suggestion) {
        exchangePointService.createSubject(getNewExchangePoint(suggestion));
        increaseSuggesterScore(getUser(suggestion));
        getService(suggestion).update(suggestion);
    }

    private ExchangePoint getNewExchangePoint(NewExchangePointSuggestion suggestion) {
        String id = idProvider.provideNewId();
        updateSuggestion(suggestion, id);
        return (ExchangePoint) new ExchangePoint()
                .setPosition(suggestion.getPosition())
                .setId(id)
                .setHonestyStatus(HonestyStatus.UNKNOWN)
                .setImage(suggestion.getImage());
    }

}
