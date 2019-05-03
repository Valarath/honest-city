package cz.honestcity.service.user;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.base.AbstractServiceTest;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.suggestion.base.BaseSuggestionService;
import cz.honestcity.service.suggestion.exchange.closed.ClosedExchangePointSuggestionService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest extends AbstractServiceTest {

    public static final int NUMBER_OF_EXPECTED_SUGGESTION_TYPES = 1;
    public static final int NUMBER_OF_EXPECTED_SUGGESTIONS = 1;
    @InjectMocks
    private UserService service;

    @Mock
    private UserGateway gateway;

    @Spy
    private HashMap<String, SuggestionService> suggestionServices;

    @Mock
    private ClosedExchangePointSuggestionService closedExchangePointSuggestionService;

    private BaseSuggestionService baseSuggestionService;


    @Test
    public void getUser() {
        User user = getUserForTest();
        prepareEnvironmentForGetUser(user);
        assertEquals(user,service.getUser(user.getId()));
    }

    private void prepareEnvironmentForGetUser(User user) {
        when(gateway.getUser(user.getId())).thenReturn(user);
    }

    @Test
    public void getUserScore() {
        User user = getUserForTest();
        prepareEnvironmentForGetUserScore(user);
        assertEquals(user.getScore(),service.getUserScore(user.getId()));
    }

    private void prepareEnvironmentForGetUserScore(User user) {
        when(gateway.getUserScore(user.getId())).thenReturn(user.getScore());
    }

    @Test
    public void getUserSuggestions_zeroSuggestions() {
        assertTrue(service.getUserSuggestions(USER_ID).isEmpty());
    }

    @Test
    public void getUserSuggestions_onlyBaseService() {
        prepareEnvironmentForGetUserSuggestionsOnlyBaseService();
        assertTrue(service.getUserSuggestions(USER_ID).isEmpty());
    }

    private void prepareEnvironmentForGetUserSuggestionsOnlyBaseService() {
        suggestionServices.put(SuggestionServiceType.SuggestionServiceTypeNames.BASE_SERVICE,baseSuggestionService);
    }

    @Test
    public void getUserSuggestions_oneSuggestion() {
        ClosedExchangePointSuggestion suggestion = getClosedExchangePointSuggestionForTest();
        prepareEnvironmentForGetUserSuggestionsOneSuggestion(suggestion);
        checkResultOfGetUserSuggestionsOneSuggestion(suggestion);
    }

    private void checkResultOfGetUserSuggestionsOneSuggestion(ClosedExchangePointSuggestion suggestion) {
        Map<Class<? extends Suggestion>, List<? extends Suggestion>> userSuggestions = service.getUserSuggestions(USER_ID);
        assertFalse(userSuggestions.isEmpty());
        assertEquals(NUMBER_OF_EXPECTED_SUGGESTION_TYPES, userSuggestions.size());
        assertEquals(NUMBER_OF_EXPECTED_SUGGESTIONS, userSuggestions.get(ClosedExchangePointSuggestion.class).size());
        assertTrue(userSuggestions.get(ClosedExchangePointSuggestion.class).contains(suggestion));
    }

    private void prepareEnvironmentForGetUserSuggestionsOneSuggestion(ClosedExchangePointSuggestion suggestion) {
        ArrayList<ClosedExchangePointSuggestion> closedExchangePointSuggestions = new ArrayList<>();
        closedExchangePointSuggestions.add(suggestion);
        suggestionServices.put(SuggestionServiceType.SuggestionServiceTypeNames.BASE_SERVICE,baseSuggestionService);
        suggestionServices.put(SuggestionServiceType.SuggestionServiceTypeNames.CLOSED_EXCHANGE_POINT,closedExchangePointSuggestionService);
        doReturn(closedExchangePointSuggestions).when(closedExchangePointSuggestionService).getUserSuggestions(USER_ID);
    }

    @Test
    public void increaseUserScore() {
        User user = getUserForTest();
        int expectedScore = user.getScore() + 1;
        prepareEnvironmentForIncreaseUserScore(user);
        service.increaseUserScore(user);
        assertEquals(expectedScore,user.getScore());
    }

    private void prepareEnvironmentForIncreaseUserScore(User user) {
        doNothing().when(gateway).setUserScore(user);
    }

    @Test
    public void saveNewUser() {
        User user = getUserForTest();
        prepareEnvironmentForSaveNewUser(user);
        service.saveNewUser(user);
    }

    private void prepareEnvironmentForSaveNewUser(User user) {
        doNothing().when(gateway).saveNewUser(user);
    }

    @Test
    public void updateUserData() {
        User user = getUserForTest();
        prepareEnvironmentForUpdateUserData(user);
        service.updateUserData(user);
    }

    private void prepareEnvironmentForUpdateUserData(User user) {
        doNothing().when(gateway).updateUserData(user);
    }
}