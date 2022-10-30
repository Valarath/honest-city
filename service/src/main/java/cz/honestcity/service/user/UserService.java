package cz.honestcity.service.user;

import cz.honestcity.model.login.LoginData;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.login.LoginDataService;
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
    private final LoginDataService loginDataService;

    public UserService(Map<String, LoginGateway> loginGateways, UserGateway userGateway, LoginDataService loginDataService) {
        this.loginGateways = loginGateways;
        this.userGateway = userGateway;
        this.loginDataService = loginDataService;
    }

    public User getUser(String userId) {
        return userGateway.getUser(userId)
                .setLoginData(loginDataService.get(userId));
    }

    public User getUserByUsername(String username) {
        User user = userGateway.getUserByUsername(username);
        return user.setLoginData(loginDataService.get(user.getId()));
    }

    public int getUserScore(String userId) {
        return userGateway.getUserScore(userId);
    }

    public Map<String, List<? extends Suggestion>> getUserSuggestions(String userId) {
        var userSuggestions = new HashMap<String, List<? extends Suggestion>>();
        suggestionServices.entrySet().forEach(entry -> {
            if (!entry.getKey().equals(SuggestionServiceType.SuggestionServiceTypeNames.BASE_SERVICE))
                userSuggestions.putAll(addUserSuggestions(userId, entry));
        });
        return userSuggestions;
    }

    private Map<String, List<? extends Suggestion>> addUserSuggestions(String userId, Map.Entry<String, SuggestionService> entry) {
        var userSuggestions = new HashMap<String, List<? extends Suggestion>>();
        var suggestions = entry.getValue().getUserSuggestions(userId);
        if (suggestions != null && !suggestions.isEmpty())
            userSuggestions.put(entry.getKey(), suggestions);
        return userSuggestions;
    }

    public void increaseUserScore(User user) {
        userGateway.setUserScore(getIncreasedScore(user));
    }

    private User getIncreasedScore(User user) {
        return user.setScore(user.getScore() + 1);
    }

    public void saveNewUser(User user) {
        userGateway.saveNewUser(user);
        loginDataService.save(user.getLoginData());
    }

    public void updateUserData(User user) {
        userGateway.updateUserData(user);
    }

    public User register(LoginData loginData) {
        String userId = loginDataService.getUserIdIfAlreadyExist(loginData);
        loginData.setUserId(userId);
        return userId == null ? performRegistration(loginData) : login(getUser(userId),loginData);
    }

    private User performRegistration(LoginData loginData) {
        User user = getLoginGateway(loginData).getUser(loginData);
        saveNewUser(user);
        return user;
    }

    public User login(User user) {
        return login(user,user.getLoginData());
    }
    public User login(User user,LoginData loginData) {
        User updatedUserData = getLoginGateway(loginData).getUser(loginData, getUser(user.getId()));
        User updatedUser = updateUser(getUser(user.getId()), updatedUserData);
        updateUserData(updatedUser);
        return updatedUser;
    }

    private User updateUser(User actualUser, User updatedUser) {
        return actualUser
                .setEmail(updatedUser.getEmail())
                .setUsername(updatedUser.getUsername())
                .setLoginData(updatedUser.getLoginData());
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
