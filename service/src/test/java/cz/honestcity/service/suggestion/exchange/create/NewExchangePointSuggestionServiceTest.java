package cz.honestcity.service.suggestion.exchange.create;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.service.base.AbstractServiceTest;
import cz.honestcity.service.suggestion.SuggestionServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NewExchangePointSuggestionServiceTest extends SuggestionServiceTest {

    @InjectMocks
    private NewExchangePointSuggestionService service;

    @Mock
    private NewExchangePointSuggestionGateway gateway;

    @Before
    public void setup(){
        super.setup();
        setupGateway(gateway);
    }

    @Test
    public void suggest() {
        var suggestions = getSuggestionsForTest(NewExchangePointSuggestion.class);
        prepareEnvironmentForSuggestTest(suggestions);
        service.suggest(suggestions);
    }

    @Test
    public void getSuggestion() {
        NewExchangePointSuggestion suggestion = getNewExchangePointSuggestionForTest();
        prepareEnvironmentForGetSuggestionTest(suggestion);
        assertEquals(suggestion,service.getSuggestion(SUGGESTION_ID));
    }

    @Test
    public void getUserSuggestions() {
        var suggestionsForTest = getSuggestionsForTest(NewExchangePointSuggestion.class);
        prepareEnvironmentForGetUserSuggestionsTest(suggestionsForTest);
        assertEquals(suggestionsForTest,service.getUserSuggestions(USER_ID));
    }

}