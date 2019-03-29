package cz.honestcity.service.user;

import cz.honestcity.model.user.User;
import cz.honestcity.service.gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void increaseUserScore(User user) {
        userGateway.increaseUserScore(user.getId(), getIncreasedScore(user));
    }

    private int getIncreasedScore(User user) {
        return user.getScore()+1;
    }
}
