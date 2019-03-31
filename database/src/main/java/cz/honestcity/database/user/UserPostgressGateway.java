package cz.honestcity.database.user;

import cz.honestcity.model.user.User;
import cz.honestcity.service.gateway.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class UserPostgressGateway implements UserGateway {
    @Override
    public User getUser(long userId) {
        return null;
    }

    @Override
    public void increaseUserScore(long id, int increasedScore) {

    }

    @Override
    public int getUserScore(long userId) {
        return 0;
    }
}
