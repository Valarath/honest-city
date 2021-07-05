package cz.honestcity.database.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.State;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClosedExchangePointSuggestionPostgresMapper {

    String TO_CLOSED_EXCHANGE_POINT_SUGGESTION ="toClosedExchangePointSuggestion";

    @Insert("INSERT INTO closed_exchange_point_suggestion(exchange_point_id, suggestion_id)\n" +
            "VALUES (#{suggestion.exchangePointId},#{suggestion.id});")
    void reportNonExistingPoint(@Param("suggestion")ClosedExchangePointSuggestion closedExchangePointSuggestions);

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       score,\n" +
            "       email,\n" +
            "       exchange_point_id,\n" +
            "FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join closed_exchange_point_suggestion ceps on suggestion.suggestion_id = ceps.suggestion_id\n" +
            "WHERE ceps.suggestion_id = #{suggestionId} AND status = 'IN_PROGRESS';")
    @Results(id = TO_CLOSED_EXCHANGE_POINT_SUGGESTION, value = {
            @Result(property = "id",column = "suggestion_id"),
            @Result(property = "state",column = "status"),
            @Result(property = "votes",column = "votes"),
            @Result(property = "suggestedBy.id",column = "user_id"),
            @Result(property = "suggestedBy.username",column = "username"),
            @Result(property = "suggestedBy.score",column = "score"),
            @Result(property = "suggestedBy.email",column = "email"),
            @Result(property = "subjectId",column = "exchange_point_id"),
    })
    ClosedExchangePointSuggestion getClosedExchangePointSuggestion(@Param("suggestionId") String suggestionId);

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       email,\n" +
            "       score,\n" +
            "       exchange_point_id\n" +
            " FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join closed_exchange_point_suggestion ceps on suggestion.suggestion_id = ceps.suggestion_id\n" +
            " WHERE  suggestion.user_id  = #{userId};")
    @ResultMap(TO_CLOSED_EXCHANGE_POINT_SUGGESTION)
    List<ClosedExchangePointSuggestion> getUserClosedExchangePointSuggestions(@Param("userId")String userId);

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       email,\n" +
            "       score,\n" +
            "       exchange_point_id\n" +
            " FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join closed_exchange_point_suggestion ceps on suggestion.suggestion_id = ceps.suggestion_id\n" +
            " WHERE  exchange_point_id  = #{exchangePointId};")
    @ResultMap(TO_CLOSED_EXCHANGE_POINT_SUGGESTION)
    List<ClosedExchangePointSuggestion> getExchangePointSuggestions(@Param("exchangePointId")String exchangePointId);
}
