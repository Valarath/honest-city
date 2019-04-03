package cz.honestcity.database.user;

import cz.honestcity.model.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPostgressMapper {

    User getUser(long userId);

    void setUserScore(User user);

    int getUserScore(long userId);

    void saveNewUser(User user);

    void updateUserData(User user);
}
