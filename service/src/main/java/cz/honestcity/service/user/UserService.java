package cz.honestcity.service.user;

import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    protected final Map<String, SuggestionService> suggestionServices;
    private final UserGateway userGateway;

    public UserService(UserGateway userGateway, Map<String, SuggestionService> suggestionServices) {
        this.userGateway = userGateway;
        this.suggestionServices = suggestionServices;
    }

    public User getUser(long userId) {
        return userGateway.getUser(userId);
    }

    public int getUserScore(long userId) {
        return userGateway.getUserScore(userId);
    }

    public Map<Class<? extends Suggestion>, List<? extends Suggestion>> getUserSuggestions(long userId) {
        var userSuggestions = new HashMap<Class<? extends Suggestion>,List<? extends Suggestion>>();
        suggestionServices.entrySet().forEach( entry -> {
            if(!entry.getKey().equals(SuggestionServiceType.SuggestionServiceTypeNames.BASE_SERVICE))
                userSuggestions.putAll(addUserSuggestions(userId,entry));
        });
        return  userSuggestions;
    }

    private Map<Class<? extends Suggestion>,List<? extends Suggestion>> addUserSuggestions(long userId, Map.Entry<String, SuggestionService> entry) {
        var userSuggestions = new HashMap<Class<? extends Suggestion>,List<? extends Suggestion>>();
        var suggestions = entry.getValue().getUserSuggestions(userId);
        if(!suggestions.isEmpty())
            userSuggestions.put(suggestions.get(0).getClass(),suggestions);
        return userSuggestions;
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
