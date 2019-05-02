package cz.honestcity.service.suggestion.exchange.rate;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.service.base.AbstractServiceTest;
import cz.honestcity.service.suggestion.SuggestionServiceTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ExchangeRateSuggestionServiceTest extends SuggestionServiceTest {

    @InjectMocks
    private ExchangeRateSuggestionService service;

    @Mock
    private ExchangeRateSuggestionGateway gateway;

    @Test
    public void suggest() {
        var suggestions = getSuggestionsForTest(ExchangeRateSuggestion.class);
        prepareEnvironmentForSuggestTest(suggestions);
        service.suggest(suggestions);
    }

    private void prepareEnvironmentForSuggestTest(List<? extends ExchangeRateSuggestion> suggestions) {
        doNothing().when(gateway).suggestsExchangeRateChange((List<ExchangeRateSuggestion>) suggestions);
    }

    @Test
    public void getSuggestion() {
        ExchangeRateSuggestion suggestion = getExchangeRateSuggestionForTest();
        prepareEnvironmentForGetSuggestionTest(suggestion);
        assertEquals(suggestion,service.getSuggestion(SUGGESTION_ID));
    }

    private void prepareEnvironmentForGetSuggestionTest(ExchangeRateSuggestion suggestion) {
        when(gateway.getExchangeRateSuggestion(suggestion.getId())).thenReturn(suggestion);
    }

    @Test
    public void getScoredSuggestions() {
    }

    @Test
    public void getUserSuggestions() {
        var suggestionsForTest = getSuggestionsForTest(ExchangeRateSuggestion.class);
        prepareEnvironmentForGetUserSuggestionsTest(suggestionsForTest);
        assertEquals(suggestionsForTest,service.getUserSuggestions(USER_ID));
    }

    private void prepareEnvironmentForGetUserSuggestionsTest(List<? extends ExchangeRateSuggestion> suggestionsForTest) {
        doReturn(suggestionsForTest).when(gateway).getUserSuggestions(USER_ID);
    }
}