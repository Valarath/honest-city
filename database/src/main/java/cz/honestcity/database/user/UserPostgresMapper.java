package cz.honestcity.database.user;

import cz.honestcity.database.suggestion.exchange.rate.ExchangeRatePostgresSuggestion;
import cz.honestcity.database.suggestion.NewExchangePointPostgresSuggestion;
import cz.honestcity.database.suggestion.exchange.closed.ClosedExchangePointPostgresSuggestion;
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

    List<ExchangeRatePostgresSuggestion> getUserExchangeRateSuggestions(@Param("userId") long userId);

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       score,\n" +
            "       latitude,\n" +
            "       longitude\n" +
            "FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join new_exchange_point_suggestion neps on suggestion.suggestion_id = neps.suggestion_id\n" +
            "where suggestion.user_id = #{userId};")
    @ConstructorArgs(value = {
            @Arg(column = "longitude",javaType = Double.class),
            @Arg(column = "latitude",javaType = Double.class),
            @Arg(column = "suggestion_id",javaType = Long.class),
            @Arg(column = "user_id",javaType = Long.class),
            @Arg(column = "username",javaType = String.class),
            @Arg(column = "score",javaType = Integer.class),
            @Arg(column = "status",javaType = State.class, typeHandler = EnumTypeHandler.class)
    })
    List<NewExchangePointPostgresSuggestion> getUserNewExchangePointSuggestions(@Param("userId") long userId);

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       score,\n" +
            "       exchange_point_id,\n" +
            "FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join closed_exchange_point_suggestion ceps on suggestion.suggestion_id = ceps.suggestion_id\n" +
            "where  suggestion.user_id  = #{userId};")
    @ConstructorArgs(value = {
            @Arg(column = "exchange_point_id",javaType = Long.class),
            @Arg(column = "suggestion_id",javaType = Long.class),
            @Arg(column = "user_id",javaType = Long.class),
            @Arg(column = "username",javaType = String.class),
            @Arg(column = "score",javaType = Integer.class),
            @Arg(column = "status",javaType = State.class, typeHandler = EnumTypeHandler.class),
    })
    List<ClosedExchangePointPostgresSuggestion> getUserNonExistingExchangePointSuggestions(@Param("userId")long userId);
}
