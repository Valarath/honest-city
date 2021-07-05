package cz.honestcity.database.suggestion.exchange.create;

import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewExchangePointSuggestionPostgresMapper {

    String TO_NEW_EXCHANGE_POINT_SUGGESTION ="toNewExchangePointSuggestion";

    @Insert("INSERT INTO new_exchange_point_suggestion(suggestion_id, latitude, longitude)\n" +
            "VALUES (#{suggestion.id},#{suggestion.position.latitude},#{suggestion.position.longitude});")
    void suggestsNewExchangePoint(@Param("suggestion") NewExchangePointSuggestion suggestions);

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       email,\n" +
            "       score,\n" +
            "       latitude,\n" +
            "       longitude,\n" +
            "       exchange_point_id\n" +
            "FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join new_exchange_point_suggestion neps on suggestion.suggestion_id = neps.suggestion_id\n" +
            "where neps.suggestion_id = #{suggestionId} AND status = 'IN_PROGRESS';")
    @ResultMap(TO_NEW_EXCHANGE_POINT_SUGGESTION)
    NewExchangePointSuggestion getNewExchangePointSuggestion(@Param("suggestionId") String suggestionId);

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       email,\n" +
            "       score,\n" +
            "       latitude,\n" +
            "       longitude,\n" +
            "       exchange_point_id\n" +
            "FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join new_exchange_point_suggestion neps on suggestion.suggestion_id = neps.suggestion_id\n" +
            "where suggestion.user_id = #{userId};")
    @ResultMap(TO_NEW_EXCHANGE_POINT_SUGGESTION)
    List<NewExchangePointSuggestion> getUserNewExchangePointSuggestions(@Param("userId") String userId);

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       email,\n" +
            "       score,\n" +
            "       latitude,\n" +
            "       longitude,\n" +
            "       exchange_point_id\n" +
            "FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join new_exchange_point_suggestion neps on suggestion.suggestion_id = neps.suggestion_id;")
    @Results(id = TO_NEW_EXCHANGE_POINT_SUGGESTION, value = {
            @Result(property = "id",column = "suggestion_id"),
            @Result(property = "state",column = "status"),
            @Result(property = "votes",column = "votes"),
            @Result(property = "suggestedBy.id",column = "user_id"),
            @Result(property = "suggestedBy.username",column = "username"),
            @Result(property = "suggestedBy.score",column = "score"),
            @Result(property = "suggestedBy.email",column = "email"),
            @Result(property = "position.latitude",column = "latitude"),
            @Result(property = "position.longitude",column = "longitude"),
            @Result(property = "subjectId",column = "exchange_point_id")
    })
    List<NewExchangePointSuggestion> getAllNewExchangePointSuggestions();

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       email,\n" +
            "       score,\n" +
            "       latitude,\n" +
            "       longitude,\n" +
            "       exchange_point_id\n" +
            "FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join new_exchange_point_suggestion neps on suggestion.suggestion_id = neps.suggestion_id\n" +
            "where exchange_point_id = #{exchangePointId};")
    @ResultMap(TO_NEW_EXCHANGE_POINT_SUGGESTION)
    List<NewExchangePointSuggestion> getExchangePointSuggestions(@Param("exchangePointId")String exchangePointId);

    @Update("UPDATE new_exchange_point_suggestion \n"+
            "SET exchange_point_id = #{suggestion.subjectId} \n" +
            "WHERE suggestion_id = #{suggestion.id} ")
    void update(@Param("suggestion") NewExchangePointSuggestion suggestion);
}
