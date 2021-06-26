package cz.honestcity.service.user;

import cz.honestcity.model.login.LoginData;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.login.LoginGateway;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Map<String, LoginGateway> loginGateways;
    private Map<String, SuggestionService> suggestionServices;
    private final UserGateway userGateway;

    public UserService(Map<String, LoginGateway> loginGateways, UserGateway userGateway) {
        this.loginGateways = loginGateways;
        this.userGateway = userGateway;
    }

    public User getUser(String userId) {
        return userGateway.getUser(userId);
    }

    public User getUserByUsername(String username) {
        return userGateway.getUserByUsername(username);
    }

    public int getUserScore(String userId) {
        return userGateway.getUserScore(userId);
    }

    public Map<Class<? extends Suggestion>, List<? extends Suggestion>> getUserSuggestions(String userId) {
        var userSuggestions = new HashMap<Class<? extends Suggestion>,List<? extends Suggestion>>();
        suggestionServices.entrySet().forEach( entry -> {
            if(!entry.getKey().equals(SuggestionServiceType.SuggestionServiceTypeNames.BASE_SERVICE))
                userSuggestions.putAll(addUserSuggestions(userId,entry));
        });
        return  userSuggestions;
    }

    private Map<Class<? extends Suggestion>,List<? extends Suggestion>> addUserSuggestions(String userId, Map.Entry<String, SuggestionService> entry) {
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

    public User register(LoginData loginData){
        User user = getLoginGateway(loginData).getUser(loginData);
        saveNewUser(user);
        return user;
    }

    public User register(User user){
        User updatedUserData = getLoginGateway(user.getLoginData()).getUser(user.getLoginData());
        User updatedUser = updateUser(user, updatedUserData);
        updateUserData(updatedUser);
        return updatedUser;
    }

    private User updateUser(User actualUser, User updatedUser){
        return actualUser
                .setEmail(updatedUser.getEmail())
                .setUsername(updatedUser.getUsername());
    }

    private LoginGateway<? super LoginData> getLoginGateway(LoginData loginData) {
        return loginGateways.get(loginData.getClass().getSimpleName());
    }

    @Autowired
    @Lazy
    public void setSuggestionServices(Map<String, SuggestionService> suggestionServices) {
        this.suggestionServices = suggestionServices;
    }
}
