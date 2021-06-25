package cz.honestcity.database.user;

import cz.honestcity.database.suggestion.exchange.closed.ClosedExchangePointPostgresSuggestion;
import cz.honestcity.database.suggestion.exchange.create.NewExchangePointPostgresSuggestion;
import cz.honestcity.database.suggestion.exchange.rate.ExchangeRatePostgresSuggestion;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.user.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

import java.util.List;

@Mapper
public interface UserPostgresMapper {

    @Select("SELECT\n" +
            "  user_id,\n" +
            "  score,\n" +
            "  username\n" +
            "FROM \"user\"\n" +
            "WHERE user_id = #{userId}")
    @Results(value = {
            @Result(property = "id",column = "user_id"),
            @Result(property = "score",column = "score"),
            @Result(property = "username",column = "username")
    })
    User getUser(@Param("userId") String userId);

    @Update("UPDATE \"user\"\n" +
            "SET score = #{user.score}\n" +
            "WHERE user_id = #{user.id}")
    void setUserScore(@Param("user") User user);

    @Select("SELECT score\n" +
            "FROM \"user\"\n" +
            "WHERE user_id = #{userId};")
    int getUserScore(@Param("userId") String userId);

    @Insert("INSERT INTO \"user\" (username, score) VALUES (#{user.username}, #{user.score});")
    void saveNewUser(@Param("user")User user);

    @Update("UPDATE \"user\"\n" +
            "SET username = #{user.username}\n" +
            "WHERE user_id = #{user.id};")
    void updateUserData(@Param("user")User user);

}
