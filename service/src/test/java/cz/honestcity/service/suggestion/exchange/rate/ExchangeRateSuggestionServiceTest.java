package cz.honestcity.service.suggestion.exchange.rate;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.service.suggestion.SuggestionServiceTest;
import org.junit.Before;
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

    @Before
    public void setup(){
        super.setup();
        setupGateway(gateway);
    }

    @Test
    public void suggest() {
        var suggestions = getSuggestionsForTest(ExchangeRateSuggestion.class);
        prepareEnvironmentForSuggestTest(suggestions);
        service.suggest(suggestions, loggedUser);
    }

    @Test
    public void getSuggestion() {
        ExchangeRateSuggestion suggestion = getExchangeRateSuggestionForTest();
        prepareEnvironmentForGetSuggestionTest(suggestion);
        assertEquals(suggestion,service.getSuggestion(SUGGESTION_ID));
    }

    @Test
    public void getScoredSuggestions() {
        List<? extends ExchangeRateSuggestion> suggestions = getSuggestionsForTest(ExchangeRateSuggestion.class);
        prepareEnvironmentForGetScoredSuggestions(suggestions);
        assertEquals(suggestions,service.getScoredSuggestions(EXCHANGE_POINT_ID));
    }

    private void prepareEnvironmentForGetScoredSuggestions(List<? extends ExchangeRateSuggestion> suggestions) {
        doReturn(suggestions).when(gateway).getExchangePointSuggestions(EXCHANGE_POINT_ID);
    }

    @Test
    public void getUserSuggestions() {
        var suggestionsForTest = getSuggestionsForTest(ExchangeRateSuggestion.class);
        prepareEnvironmentForGetUserSuggestionsTest(suggestionsForTest);
        assertEquals(suggestionsForTest,service.getUserSuggestions(USER_ID));
    }

}