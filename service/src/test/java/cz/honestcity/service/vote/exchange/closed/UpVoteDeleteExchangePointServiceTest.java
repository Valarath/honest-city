package cz.honestcity.service.vote.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.suggestion.exchange.closed.ClosedExchangePointSuggestionService;
import cz.honestcity.service.vote.exchange.VoteExchangeServiceTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
        ClosedExchangePointSuggestion suggestion = getClosedExchangePointSuggestion();
        setCommonEnvironment(suggestion);
        setSuggestionService(suggestion);
        doNothing().when(exchangeService).deleteExchangePoint(suggestion.getExchangePointId());
    }

    private void setSuggestionService(ClosedExchangePointSuggestion suggestion) {
        when(suggestionServices.get(SuggestionServiceType.CLOSED_EXCHANGE_POINT.name())).thenReturn(closedExchangePointSuggestionService);
        when(closedExchangePointSuggestionService.getSuggestion(SUGGESTION_ID)).thenReturn(suggestion);
    }

    private ClosedExchangePointSuggestion getClosedExchangePointSuggestion() {
        return (ClosedExchangePointSuggestion) new ClosedExchangePointSuggestion()
                .setExchangePointId(EXCHANGE_POINT_ID)
                .setSuggestedBy(getUser());
    }

}
