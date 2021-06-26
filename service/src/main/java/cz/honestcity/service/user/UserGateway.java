package cz.honestcity.service.user;

import cz.honestcity.model.user.User;

public interface UserGateway {

    User getUser(String userId);

    void setUserScore(User user);

    int getUserScore(String userId);

    void saveNewUser(User user);

    void updateUserData(User user);

    User getUserByUsername(String username);
}
