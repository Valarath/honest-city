package cz.honestcity.service.vote.exchange.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.suggestion.exchange.rate.ExchangeRateSuggestionService;
import cz.honestcity.service.vote.exchange.VoteExchangeServiceTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doNothing;

public class UpVoteExchangePointRateChangeServiceTest extends VoteExchangeServiceTest {

    @InjectMocks
    private UpVoteExchangePointRateChangeService service;

    @Mock
    private ExchangeRateSuggestionService exchangeRateSuggestionService;

    @Test
    public void upVote_suggestionAccepted() {
        prepareEnvironment_suggestionAcceptedTest();
        service.upVote(getClosedExchangePointSuggestionForTest(),USER_ID);
    }

    private void prepareEnvironment_suggestionAcceptedTest() {
        ExchangeRateSuggestion suggestion = getExchangeRateSuggestionForTest();
        setCommonEnvironment(suggestion, SuggestionServiceType.EXCHANGE_RATE_CHANGE,exchangeRateSuggestionService);
        doNothing().when(exchangeService).changeExchangeRate(EXCHANGE_RATE_ID,EXCHANGE_POINT_ID);
    }

    @Test
    public void upVote_suggestionNotAccepted() {
        upVote_suggestionNotAccepted(service);
    }
}