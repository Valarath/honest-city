package cz.honestcity.service.gateway;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;

import java.util.List;

public interface UserGateway {

    User getUser(long userId);

    void setUserScore(User user);

    int getUserScore(long userId);

    void saveNewUser(User user);

    void updateUserData(User user);

    List<? extends ExchangeRateSuggestion> getUserExchangeRateSuggestions(long userId);

    List<? extends NewExchangePointSuggestion> getUserNewExchangePointSuggestions(long userId);

    List<? extends NonExistingExchangePointSuggestion> getUserNonExistingExchangePointSuggestions(long userId);
}
