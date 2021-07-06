package cz.honestcity.database.suggestion.exchange.rate;

import cz.honestcity.database.rate.PostgresRate;
import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.State;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Mapper
@Service
public interface ExchangeRateSuggestionPostgresMapper {

    String TO_EXCHANGE_RATE_SUGGESTION ="toExchangeRateSuggestion";

    @Insert("INSERT INTO exchange_rate_suggestion(exchange_point_id, suggestion_id, exchange_rates_id)\n" +
            "values(#{suggestion.subjectId},#{suggestion.id},#{suggestion.suggestedExchangeRate.id});")
    void suggestsExchangeRateChange(@Param("suggestion")ExchangeRateSuggestion suggestions);

    @Select("SELECT\n" +
            "  suggestion2.suggestion_id,\n" +
            "  exchange_point_id,\n" +
            "  u.user_id,\n" +
            "  username,\n" +
            "  email,\n" +
            "  status,\n" +
            "  votes,\n" +
            "  score,\n" +
            "  exchange_rates_id \n" +
            "FROM suggestion\n" +
            "  JOIN exchange_rate_suggestion suggestion2 on suggestion.suggestion_id = suggestion2.suggestion_id\n" +
            "  JOIN \"user\" u on suggestion.user_id = u.user_id\n" +
            "WHERE suggestion2.suggestion_id =#{suggestionId}")

    @Results(id = TO_EXCHANGE_RATE_SUGGESTION, value = {
            @Result(property = "id",column = "suggestion_id"),
            @Result(property = "state",column = "status"),
            @Result(property = "votes",column = "votes"),
            @Result(property = "suggestedBy.id",column = "user_id"),
            @Result(property = "suggestedBy.username",column = "username"),
            @Result(property = "suggestedBy.score",column = "score"),
            @Result(property = "suggestedBy.email",column = "email"),
            @Result(property = "exchangeRate.id",column = "exchange_rates_id"),
            @Result(property = "subjectId",column = "exchange_point_id"),
    })
    ExchangeRateSuggestion getExchangeRateSuggestion(@Param("suggestionId")String suggestionId);

    @Select("SELECT\n" +
            "  suggestion2.suggestion_id,\n" +
            "  exchange_point_id,\n" +
            "  u.user_id,\n" +
            "  username,\n" +
            "  email,\n" +
            "  status,\n" +
            "  votes,\n" +
            "  score,\n" +
            "  exchange_rates_id \n" +
            "FROM suggestion\n" +
            "  JOIN exchange_rate_suggestion suggestion2 on suggestion.suggestion_id = suggestion2.suggestion_id\n" +
            "  JOIN \"user\" u on suggestion.user_id = u.user_id\n" +
            "WHERE exchange_point_id = #{exchangePointId};")
    @ResultMap(TO_EXCHANGE_RATE_SUGGESTION)
    List<ExchangeRateSuggestion> getExchangePointSuggestions(@Param("exchangePointId") String exchangePointId);

    @Select("SELECT\n" +
            "  buy,\n" +
            "  currency_shortcut \n" +
            "FROM exchange_rate \n" +
            "where exchange_rates_id = #{exchangeRatesId}")
    @ConstructorArgs(value = {
            @Arg(column = "currency", javaType = Currency.class),
            @Arg(column = "buy", javaType = Integer.class)
    })
    Set<PostgresRate> getSuggestedRates(@Param("exchangeRatesId") String exchangeRatesId);

    @Select("SELECT\n" +
            "  suggestion2.suggestion_id,\n" +
            "  exchange_point_id,\n" +
            "  u.user_id,\n" +
            "  username,\n" +
            "  email,\n" +
            "  status,\n" +
            "  votes,\n" +
            "  score,\n" +
            "  exchange_rates_id \n" +
            "FROM suggestion\n" +
            "  JOIN exchange_rate_suggestion suggestion2 on suggestion.suggestion_id = suggestion2.suggestion_id\n" +
            "  JOIN \"user\" u on suggestion.user_id = u.user_id\n" +
            "WHERE u.user_id = #{userId};")
    @ResultMap(TO_EXCHANGE_RATE_SUGGESTION)
    List<ExchangeRateSuggestion> getUserExchangeRateSuggestions(@Param("userId") String userId);
}
