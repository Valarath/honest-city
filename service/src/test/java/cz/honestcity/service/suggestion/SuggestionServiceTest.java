package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.base.AbstractServiceTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public abstract class SuggestionServiceTest extends AbstractServiceTest {

    private SuggestionGateway gateway;

    protected void setupGateway(SuggestionGateway gateway){
        this.gateway = gateway;
    }


    protected  <T> List<? extends T> getSuggestionsForTest(Class<T> clazz) {
        return new ArrayList<T>();
    }

    protected void prepareEnvironmentForSuggestTest(List<? extends Suggestion> suggestions) {
        doNothing().when(gateway).suggests(suggestions);
    }

    protected void prepareEnvironmentForGetSuggestionTest(Suggestion suggestion) {
        when(gateway.getSuggestion(suggestion.getId())).thenReturn(suggestion);
    }

    protected void prepareEnvironmentForGetUserSuggestionsTest(List<? extends Suggestion> suggestionsForTest) {
        doReturn(suggestionsForTest).when(gateway).getUserSuggestions(USER_ID);
    }
}
