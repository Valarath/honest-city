package cz.honestcity.service.vote.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.suggestion.exchange.closed.ClosedExchangePointSuggestionService;
import cz.honestcity.service.vote.exchange.VoteExchangeServiceTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doNothing;

public class UpVoteDeleteExchangePointServiceTest extends VoteExchangeServiceTest {

    @InjectMocks
    private UpVoteDeleteExchangePointService service;

    @Mock
    private ClosedExchangePointSuggestionService closedExchangePointSuggestionService;

    @Test
    public void upVote_suggestionAccepted(){
        prepareEnvironment_suggestionAcceptedTest();
        service.upVote(SUGGESTION_ID,USER_ID);
    }

    private void prepareEnvironment_suggestionAcceptedTest(){
        ClosedExchangePointSuggestion suggestion = getClosedExchangePointSuggestionForTest();
        setCommonEnvironment(suggestion,SuggestionServiceType.CLOSED_EXCHANGE_POINT,closedExchangePointSuggestionService);
        doNothing().when(exchangeService).deleteExchangePoint(suggestion.getExchangePointId());
    }

    @Test
    public void upVote_suggestionNotAccepted(){
       upVote_suggestionNotAccepted(service);
    }
}
