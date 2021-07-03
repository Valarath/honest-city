package cz.honestcity.service.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.service.suggestion.SuggestionServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class ClosedExchangePointSuggestionServiceTest extends SuggestionServiceTest {

    @InjectMocks
    private ClosedExchangePointSuggestionService service;

    @Mock
    private ClosedExchangePointSuggestionGateway gateway;

    @Before
    public void setup(){
        super.setup();
        setupGateway(gateway);
    }

    @Test
    public void suggest() {
        var suggestions = getSuggestionsForTest(ClosedExchangePointSuggestion.class);
        prepareEnvironmentForSuggestTest(suggestions);
        service.suggest(suggestions, loggedUser);
    }

    @Test
    public void getSuggestion() {
        ClosedExchangePointSuggestion suggestion = getClosedExchangePointSuggestionForTest();
        prepareEnvironmentForGetSuggestionTest(suggestion);
        assertEquals(suggestion,service.getSuggestion(SUGGESTION_ID));
    }

    @Test
    public void getUserSuggestions() {
        var suggestionsForTest = getSuggestionsForTest(ClosedExchangePointSuggestion.class);
        prepareEnvironmentForGetUserSuggestionsTest(suggestionsForTest);
        assertEquals(suggestionsForTest,service.getUserSuggestions(USER_ID));
    }

}