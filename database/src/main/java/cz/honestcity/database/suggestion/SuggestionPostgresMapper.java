package cz.honestcity.database.suggestion;

import cz.honestcity.model.suggestion.*;
import org.apache.ibatis.annotations.*;

import java.awt.*;
import java.util.List;

@Mapper
public interface SuggestionPostgresMapper {

    List<ExchangeRateSuggestion> getExchangePointSuggestions(long exchangePointId);

    @Insert("INSERT INTO closed_exchange_point_suggestion(exchange_point_id, suggestion_id)\n" +
            "VALUES (#{suggestion.exchangePointId},#{suggestion.id});")
    void reportNonExistingPoint(@Param("suggestion")NonExistingExchangePointSuggestion nonExistingExchangePointSuggestions);

    @Insert("INSERT INTO new_exchange_point_suggestion(suggestion_id, latitude, longitude)\n" +
            "VALUES (#{suggestion.id},#{suggestion.latitude},#{suggestion.longitude});")
    void suggestsNewExchangePoint(@Param("suggestion")NewExchangePointSuggestion suggestions);

    @Insert("INSERT INTO exchange_rate_suggestion(exchange_point_id, suggestion_id, exchange_rate_id)\n" +
            "values(#{suggestion.exchangePointId},#{suggestion.id},#{suggestion.suggestedExchangeRate.id});")
    void suggestsExchangeRateChange(@Param("suggestion")ExchangeRateSuggestion suggestions);

    @Insert("INSERT INTO suggestion(user_id, status, votes, proof)\n" +
            "values (#{suggestion.suggestedBy.id},#{suggestion.state},0,#{suggestion.proofPicture});")
    @Options(useGeneratedKeys = true, keyProperty = "suggestion.id", keyColumn = "suggestion_id")
    long suggest(@Param("suggestion") Suggestion suggestion);

    @Delete("<script>\n" +
            "DELETE\n" +
            "FROM suggestion\n" +
            "WHERE suggestion_id in\n" +
            "<foreach collection='toRemove' item='suggestion' index='index' open='(' separator =',' close=')'> \n" +
            "#{suggestion.id}" +
            "</script>")
    void removeSuggestions(@Param("toRemove") List<? extends Suggestion> toRemove);

    ExchangeRatePostgresSuggestion getExchangeRateSuggestion(@Param("suggestionId")long suggestionId);

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       score,\n" +
            "       exchange_point_id,\n" +
            "       proof\n" +
            "FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join closed_exchange_point_suggestion ceps on suggestion.suggestion_id = ceps.suggestion_id\n" +
            "where ceps.suggestion_id = #{suggestionId} AND status = 'IN_PROGRESS';")
    @ConstructorArgs(value = {
            @Arg(column = "exchange_point_id",javaType = Long.class),
            @Arg(column = "suggestion_id",javaType = Long.class),
            @Arg(column = "user_id",javaType = Long.class),
            @Arg(column = "username",javaType = String.class),
            @Arg(column = "score",javaType = Integer.class),
            @Arg(column = "proof",javaType = Image.class),
            @Arg(column = "status",javaType = State.class)
    })
    NonExistingExchangePointPostgresSuggestion getNonExistingExchangePointSuggestion(@Param("suggestionId") long suggestionId);

    @Select("SELECT suggestion.suggestion_id,\n" +
            "       votes,\n" +
            "       suggestion.user_id,\n" +
            "       status,\n" +
            "       username,\n" +
            "       score,\n" +
            "       proof,\n" +
            "       latitude,\n" +
            "       longitude\n" +
            "FROM suggestion\n" +
            "         join \"user\" u on suggestion.user_id = u.user_id\n" +
            "         join new_exchange_point_suggestion neps on suggestion.suggestion_id = neps.suggestion_id\n" +
            "where ceps.suggestion_id = #{suggestionId} AND status = 'IN_PROGRESS';")
    @ConstructorArgs(value = {
            @Arg(column = "longitude",javaType = Double.class),
            @Arg(column = "latitude",javaType = Double.class),
            @Arg(column = "suggestion_id",javaType = Long.class),
            @Arg(column = "user_id",javaType = Long.class),
            @Arg(column = "username",javaType = String.class),
            @Arg(column = "score",javaType = Integer.class),
            @Arg(column = "proof",javaType = Image.class),
            @Arg(column = "status",javaType = State.class)
    })
    NewExchangePointPostgresSuggestion getNewExchangePointSuggestion(@Param("suggestionId") long suggestionId);
}
