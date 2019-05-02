package cz.honestcity.service.vote.exchange.create;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.suggestion.exchange.create.NewExchangePointSuggestionService;
import cz.honestcity.service.vote.exchange.VoteExchangeServiceTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UpVoteNewExchangePointServiceTest extends VoteExchangeServiceTest {

    @InjectMocks
    private UpVoteNewExchangePointService service;

    @Mock
    private NewExchangePointSuggestionService newExchangePointSuggestionService;

    @Test
    public void upVote_suggestionAccepted() {
        prepareEnvironment_suggestionAcceptedTest();
        service.upVote(SUGGESTION_ID,USER_ID);
    }

    private void prepareEnvironment_suggestionAcceptedTest(){
        NewExchangePointSuggestion suggestion = getClosedExchangePointSuggestion();
        setCommonEnvironment(suggestion);
        setSuggestionService(suggestion);
        doNothing().when(exchangeService).createSubject(getNewExchangePoint(suggestion));

    }

    private void setSuggestionService(NewExchangePointSuggestion suggestion) {
        when(suggestionServices.get(SuggestionServiceType.NEW_EXCHANGE_POINT.name())).thenReturn(newExchangePointSuggestionService);
        when(newExchangePointSuggestionService.getSuggestion(SUGGESTION_ID)).thenReturn(suggestion);
    }

    private NewExchangePointSuggestion getClosedExchangePointSuggestion() {
        return (NewExchangePointSuggestion) new NewExchangePointSuggestion()
                .setPosition(getPositionForTest())
                .setSuggestedBy(getUser());
    }

    private Position getPositionForTest() {
        return new Position();
    }

    private ExchangePoint getNewExchangePoint(NewExchangePointSuggestion suggestion) {
        return (ExchangePoint) new ExchangePoint()
                .setPosition(suggestion.getPosition())
                .setHonestyStatus(HonestyStatus.UNKNOWN);
    }
}