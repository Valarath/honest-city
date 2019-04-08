package cz.honestcity.database.suggestion.exchange.rate;

import cz.honestcity.database.rate.PostgresRate;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.State;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

import java.util.List;
import java.util.Set;

@Mapper
public interface ExchangeRateSuggestionPostgresMapper {

    @Insert("INSERT INTO exchange_rate_suggestion(exchange_point_id, suggestion_id, exchange_rate_id)\n" +
            "values(#{suggestion.exchangePointId},#{suggestion.id},#{suggestion.suggestedExchangeRate.id});")
    void suggestsExchangeRateChange(@Param("suggestion")ExchangeRateSuggestion suggestions);

    @Select("SELECT\n" +
            "  suggestion2.suggestion_id,\n" +
            "  exchange_point_id,\n" +
            "  u.user_id,\n" +
            "  username,\n" +
            "  status,\n" +
            "  votes,\n" +
            "  score,\n" +
            "  exchange_rates_id\n" +
            "FROM suggestion\n" +
            "  JOIN exchange_rate_suggestion suggestion2 on suggestion.suggestion_id = suggestion2.suggestion_id\n" +
            "  JOIN \"user\" u on suggestion.user_id = u.user_id\n" +
            "WHERE suggestion2.suggestion_id =#{suggestionId}")
    @ConstructorArgs(value = {
            @Arg(column = "exchange_point_id",javaType = Long.class),
            @Arg(column = "suggestion_id",javaType = Long.class),
            @Arg(column = "user_id",javaType = Long.class),
            @Arg(column = "username",javaType = String.class),
            @Arg(column = "score",javaType = Integer.class),
            @Arg(column = "votes",javaType = Integer.class),
            @Arg(column = "status",javaType = State.class, typeHandler = EnumTypeHandler.class),
            @Arg(column = "exchange_rates_id",javaType = Long.class)
    })
    ExchangeRatePostgresSuggestion getExchangeRateSuggestion(@Param("suggestionId")long suggestionId);

    List<ExchangeRateSuggestion> getExchangePointSuggestions(long exchangePointId);

    List<ExchangeRateSuggestion> getExchangePointSuggestion(long suggestionId,long exchangePointId);

    Set<PostgresRate> getSuggestedRates(long exchangeRatesId);


}