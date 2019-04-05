package cz.honestcity.service.user;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserGateway userGateway;

    public User getUser(long userId){
        return userGateway.getUser(userId);
    }

    public int getUserScore(long userId) {
        return userGateway.getUserScore(userId);
    }

    public Map<Class<? extends Suggestion>,List<? extends Suggestion>> getUserSuggestions(long userId) {
        return Map.of(NewExchangePointSuggestion.class,userGateway.getUserNewExchangePointSuggestions(userId),
                NonExistingExchangePointSuggestion.class,userGateway.getUserNonExistingExchangePointSuggestions(userId),
                ExchangeRateSuggestion.class,userGateway.getUserExchangeRateSuggestions(userId));
    }


    public void increaseUserScore(User user) {
        userGateway.setUserScore(getIncreasedScore(user));
    }

    private User getIncreasedScore(User user) {
        return user.setScore(user.getScore()+1);
    }

    public void saveNewUser(User user){
        userGateway.saveNewUser(user);
    }

    public void updateUserData(User user){
        userGateway.updateUserData(user);
    }
}
