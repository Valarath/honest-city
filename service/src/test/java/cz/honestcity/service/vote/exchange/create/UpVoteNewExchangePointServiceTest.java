package cz.honestcity.service.vote.exchange.create;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.suggestion.exchange.create.NewExchangePointSuggestionService;
import cz.honestcity.service.vote.exchange.VoteExchangeServiceTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doNothing;

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
        NewExchangePointSuggestion suggestion = getNewExchangePointSuggestionForTest();
        setCommonEnvironment(suggestion,SuggestionServiceType.NEW_EXCHANGE_POINT,newExchangePointSuggestionService);
        doNothing().when(exchangeService).createSubject(getNewExchangePoint(suggestion));
    }

    private ExchangePoint getNewExchangePoint(NewExchangePointSuggestion suggestion) {
        return (ExchangePoint) new ExchangePoint()
                .setPosition(suggestion.getPosition())
                .setHonestyStatus(HonestyStatus.UNKNOWN);
    }

    @Test
    public void upVote_suggestionNotAccepted(){
        upVote_suggestionNotAccepted(service);
    }
}