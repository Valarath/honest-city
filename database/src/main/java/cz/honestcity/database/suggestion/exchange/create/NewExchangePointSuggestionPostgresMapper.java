package cz.honestcity.database.suggestion.exchange.create;

import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.State;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

import java.util.List;

@Mapper
public interface NewExchangePointSuggestionPostgresMapper {

    @Insert("INSERT INTO new_exchange_point_suggestion(suggestion_id, latitude, longitude)\n" +
            "VALUES (#{suggestion.id},#{suggestion.position.latitude},#{suggestion.position.longitude});")
    void suggestsNewExchangePoint(@Param("suggestion")NewExchangePointSuggestion suggestions);

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
            "where ceps.suggestion_id = #{suggestionId} AND status = 'IN_PROGRESS';")
    @ConstructorArgs(value = {
            @Arg(column = "longitude",javaType = Double.class),
            @Arg(column = "latitude",javaType = Double.class),
            @Arg(column = "suggestion_id",javaType = String.class),
            @Arg(column = "user_id",javaType = String.class),
            @Arg(column = "username",javaType = String.class),
            @Arg(column = "score",javaType = Integer.class),
            @Arg(column = "status",javaType = State.class, typeHandler = EnumTypeHandler.class)
    })
    NewExchangePointPostgresSuggestion getNewExchangePointSuggestion(@Param("suggestionId") String suggestionId);

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
            @Arg(column = "suggestion_id",javaType = String.class),
            @Arg(column = "user_id",javaType = String.class),
            @Arg(column = "username",javaType = String.class),
            @Arg(column = "score",javaType = Integer.class),
            @Arg(column = "status",javaType = State.class, typeHandler = EnumTypeHandler.class)
    })
    List<NewExchangePointPostgresSuggestion> getUserNewExchangePointSuggestions(@Param("userId") String userId);

}
