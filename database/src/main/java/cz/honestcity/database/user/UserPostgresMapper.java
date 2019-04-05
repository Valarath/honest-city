package cz.honestcity.database.user;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import org.apache.ibatis.annotations.*;

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
    User getUser(@Param("userId") long userId);

    @Update("UPDATE \"user\"\n" +
            "SET score = #{user.score}\n" +
            "WHERE user_id = #{user.id}")
    void setUserScore(@Param("user") User user);

    @Select("SELECT score\n" +
            "FROM \"user\"\n" +
            "WHERE user_id = #{userId};")
    int getUserScore(@Param("userId") long userId);

    @Insert("INSERT INTO \"user\" (username, score) VALUES (#{user.username}, #{user.score});")
    void saveNewUser(@Param("user")User user);

    @Update("UPDATE \"user\"\n" +
            "SET username = #{user.username}\n" +
            "WHERE user_id = #{user.id};")
    void updateUserData(@Param("user")User user);

    List<ExchangeRateSuggestion> getUserExchangeRateSuggestions(long userId);

    List<NewExchangePointSuggestion> getUserNewExchangePointSuggestions(long userId);

    List<NonExistingExchangePointSuggestion> getUserNonExistingExchangePointSuggestions(long userId);
}
