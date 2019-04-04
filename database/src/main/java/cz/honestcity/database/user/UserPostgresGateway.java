package cz.honestcity.database.user;

import cz.honestcity.model.user.User;
import cz.honestcity.service.gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
