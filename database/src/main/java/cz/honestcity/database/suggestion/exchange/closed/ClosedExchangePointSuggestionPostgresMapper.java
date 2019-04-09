package cz.honestcity.database.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.State;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

import java.util.List;

@Mapper
public interface ClosedExchangePointSuggestionPostgresMapper {

    @Insert("INSERT INTO closed_exchange_point_suggestion(exchange_point_id, suggestion_id)\n" +
            "VALUES (#{suggestion.exchangePointId},#{suggestion.id});")
    void reportNonExistingPoint(@Param("suggestion")ClosedExchangePointSuggestion closedExchangePointSuggestions);

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
            "WHERE ceps.suggestion_id = #{suggestionId} AND status = 'IN_PROGRESS';")
    @ConstructorArgs(value = {
            @Arg(column = "exchange_point_id",javaType = Long.class),
            @Arg(column = "suggestion_id",javaType = Long.class),
            @Arg(column = "user_id",javaType = Long.class),
            @Arg(column = "username",javaType = String.class),
            @Arg(column = "score",javaType = Integer.class),
            @Arg(column = "status",javaType = State.class, typeHandler = EnumTypeHandler.class)
    })
    ClosedExchangePointPostgresSuggestion getClosedExchangePointSuggestion(@Param("suggestionId") long suggestionId);

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
    List<ClosedExchangePointPostgresSuggestion> getUserClosedExchangePointSuggestions(@Param("userId")long userId);
}
