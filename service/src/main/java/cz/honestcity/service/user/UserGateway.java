package cz.honestcity.service.user;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.user.User;

import java.util.List;

public interface UserGateway {

    User getUser(long userId);

    void setUserScore(User user);

    int getUserScore(long userId);

    void saveNewUser(User user);

    void updateUserData(User user);

}
