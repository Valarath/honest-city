package cz.honestcity.service.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.base.AbstractServiceTest;
import cz.honestcity.service.suggestion.SuggestionServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        service.suggest(suggestions);
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