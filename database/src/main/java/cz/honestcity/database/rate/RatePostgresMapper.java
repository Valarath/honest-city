package cz.honestcity.database.rate;

import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.exchange.Rate;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

import java.time.LocalDate;
import java.util.Set;

@Mapper
public interface RatePostgresMapper {

    @Insert("INSERT INTO exchange_rates")
    @Options(useGeneratedKeys = true, keyColumn = "exchange_rates_id")
    long saveExchangeRates();

    @Insert("INSERT INTO exchange_rate (exchange_rates_id, buy, currency_shortcut) \n" +
            "\"<foreach collection='rates' item='rate' index='index' open='(' separator = '),(' close=')' >#{exchangeRatesId},#{rate.exchangeRateValues.buy},#{rate.currency}</foreach>\" +\n" +
            "\"</script>\"")
    void saveCentralAuthorityRate(@Param("rates") Set<? extends Rate> rates,@Param("exchangeRatesId") long exchangeRatesId);

    @Select("SELECT central_authority_id, active_from, active_to\n" +
            "FROM central_authority_rate\n" +
            "where active_to ISNULL;")
    @ConstructorArgs(value = {
            @Arg(column = "central_authority_id",javaType = Long.class),
            @Arg(column = "active_from",javaType = LocalDate.class),
            @Arg(column = "active_to",javaType = LocalDate.class)
    })
    ExchangePostgresRate getCentralAuthorityExchangeRate();

    @Select("SELECT exchange_point_id, active_from\n" +
            "FROM exchange_point_rate\n" +
            "where active_to ISNULL AND exchange_point_id = #{exchangePointId};")
    @ConstructorArgs(value = {
            @Arg(column = "exchange_point_id",javaType = Long.class),
            @Arg(column = "active_from",javaType = LocalDate.class),
            @Arg(column = "active_to",javaType = LocalDate.class)
    })
    ExchangePostgresRate getRate(@Param("exchangePointId") long exchangePointId);

    @Select("SELECT\n" +
            "  buy,\n" +
            "  currency_shortcut\n" +
            "FROM exchange_rate\n" +
            "where exchange_rates_id =\n" +
            "      (SELECT c2.exchange_rates_id\n" +
            "       FROM exchange_rates\n" +
            "         join central_authority_rate c2 on exchange_rates.exchange_rates_id = c2.exchange_rates_id\n" +
            "         join central_authority a on c2.central_authority_id = a.central_authority_id\n" +
            "       where a.central_authority_id notnull and active_to isnull);")

    @ConstructorArgs(value = {
            @Arg(column = "currency", javaType = Currency.class, typeHandler = EnumTypeHandler.class),
            @Arg(column = "buy", javaType = Integer.class)
    })
    Set<PostgresRate> getCentralAuthorityRates();

    @Select("SELECT\n" +
            "  buy,\n" +
            "  currency_shortcut\n" +
            "FROM exchange_rate\n" +
            "where exchange_rates_id = (SELECT exchange_rates.exchange_rates_id\n" +
            "                           FROM exchange_point_rate\n" +
            "                             join exchange_rates\n" +
            "                               on exchange_point_rate.exchange_rates_id = exchange_rates.exchange_rates_id\n" +
            "                           where exchange_point_id = #{exchangePointId} and active_to isnull);")
    @ConstructorArgs(value = {
            @Arg(column = "currency", javaType = Currency.class, typeHandler = EnumTypeHandler.class),
            @Arg(column = "buy", javaType = Integer.class)
    })
    Set<PostgresRate> getExchangePointRates(@Param("exchangePointId") long exchangePointId);
}
