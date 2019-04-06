package cz.honestcity.database.user;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPostgresGateway implements UserGateway {

    @Autowired
    private UserPostgresMapper userPostgresMapper;

    @Override
    public User getUser(long userId) {
        return userPostgresMapper.getUser(userId);
    }

    @Override
    public void setUserScore(User user) {
        userPostgresMapper.setUserScore(user);
    }

    @Override
    public int getUserScore(long userId) {
        return userPostgresMapper.getUserScore(userId);
    }

    @Override
    public void saveNewUser(User user) {
        userPostgresMapper.saveNewUser(user);
    }

    @Override
    public void updateUserData(User user) {
        userPostgresMapper.updateUserData(user);
    }

    @Override
    public List<? extends ExchangeRateSuggestion> getUserExchangeRateSuggestions(long userId) {
        return userPostgresMapper.getUserExchangeRateSuggestions(userId);
    }

    @Override
    public List<? extends NewExchangePointSuggestion> getUserNewExchangePointSuggestions(long userId) {
        return userPostgresMapper.getUserNewExchangePointSuggestions(userId);
    }

    @Override
    public List<? extends NonExistingExchangePointSuggestion> getUserNonExistingExchangePointSuggestions(long userId) {
        return userPostgresMapper.getUserNonExistingExchangePointSuggestions(userId);
    }

}
