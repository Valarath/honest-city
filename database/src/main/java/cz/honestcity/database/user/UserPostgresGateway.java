package cz.honestcity.database.user;

import cz.honestcity.model.user.User;
import cz.honestcity.service.user.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class UserPostgresGateway implements UserGateway {

    private final UserPostgresMapper userPostgresMapper;

    public UserPostgresGateway(UserPostgresMapper userPostgresMapper) {
        this.userPostgresMapper = userPostgresMapper;
    }

    @Override
    public User getUser(String userId) {
        return userPostgresMapper.getUser(userId);
    }

    @Override
    public void setUserScore(User user) {
        userPostgresMapper.setUserScore(user);
    }

    @Override
    public int getUserScore(String userId) {
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
    public User getUserByUsername(String username) {
        return userPostgresMapper.getUserByUsername(username);
    }

}
