package cz.honestcity.database.user;

import cz.honestcity.model.user.User;
import cz.honestcity.service.gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPostgressGateway implements UserGateway {

    @Autowired
    private UserPostgressMapper userPostgressMapper;

    @Override
    public User getUser(long userId) {
        return userPostgressMapper.getUser(userId);
    }

    @Override
    public void setUserScore(User user) {
        userPostgressMapper.setUserScore(user);
    }

    @Override
    public int getUserScore(long userId) {
        return userPostgressMapper.getUserScore(userId);
    }

    @Override
    public void saveNewUser(User user) {
        userPostgressMapper.saveNewUser(user);
    }

    @Override
    public void updateUserData(User user) {
        userPostgressMapper.updateUserData(user);
    }
}
