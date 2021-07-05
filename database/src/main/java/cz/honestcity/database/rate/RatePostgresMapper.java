package cz.honestcity.database.rate;

import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.exchange.ExchangeRate;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.Set;

@Mapper
public interface RatePostgresMapper {

    @Insert("INSERT INTO exchange_rates(exchange_rates_id) \n" +
            "values (#{exchangeRatesId});")
    void saveExchangeRates(@Param("exchangeRatesId") String exchangeRatesId);

    @Insert("<script> \n" +
            "INSERT INTO exchange_rate (exchange_rate_id, exchange_rates_id, buy, currency_shortcut) \n" +
            "VALUES \n" +
            "<foreach collection='exchangeRate.rates' item='rate' index='index' open='(' separator = '),(' close=')' > #{exchangeRatesId}, #{exchangeRatesId}, #{rate.rateValues.buy}, #{rate.currency}</foreach> \n" +
            "</script>")
    void saveCentralAuthorityRates(@Param("exchangeRatesId") String exchangeRatesId, @Param("exchangeRate") ExchangeRate exchangeRate);

    @Insert("INSERT INTO central_authority_rate (exchange_rates_id, central_authority_id, active_from, active_to) \n" +
            "VALUES(#{exchangeRatesId},(SELECT central_authority_id from central_authority LIMIT 1),#{exchangeRate.watched.from},#{exchangeRate.watched.to});")
    void saveCentralAuthorityRate(@Param("exchangeRatesId") String exchangeRatesId, @Param("exchangeRate") ExchangeRate exchangeRate);

    @Update("UPDATE central_authority_rate \n" +
            "set active_to = #{to} \n" +
            "WHERE active_to ISNULL")
    void disableRate(@Param("to") LocalDate to);

    @Select("SELECT central_authority_id, active_from, active_to\n" +
            "FROM central_authority_rate\n" +
            "where active_to ISNULL " +
            "ORDER BY active_from DESC " +
            "LIMIT 1;")
    @ConstructorArgs(value = {
            @Arg(column = "central_authority_id", javaType = String.class),
            @Arg(column = "active_from", javaType = LocalDate.class),
            @Arg(column = "active_to", javaType = LocalDate.class)
    })
    ExchangePostgresRate getCentralAuthorityExchangeRate();

    @Select("SELECT exchange_point_id, active_from\n" +
            "FROM exchange_point_rate\n" +
            "where active_to ISNULL AND exchange_point_id = #{exchangePointId};")
    @ConstructorArgs(value = {
            @Arg(column = "exchange_point_id", javaType = String.class),
            @Arg(column = "active_from", javaType = LocalDate.class),
            @Arg(column = "active_to", javaType = LocalDate.class)
    })
    ExchangePostgresRate getRate(@Param("exchangePointId") String exchangePointId);

    @Select("SELECT\n" +
            "  buy,\n" +
            "  currency_shortcut\n" +
            "FROM exchange_rate\n" +
            "where exchange_rates_id =\n" +
            "      (SELECT c2.exchange_rates_id\n" +
            "       FROM exchange_rates\n" +
            "         join central_authority_rate c2 on exchange_rates.exchange_rates_id = c2.exchange_rates_id\n" +
            "         join central_authority a on c2.central_authority_id = a.central_authority_id\n" +
            "       where a.central_authority_id notnull and active_to is null);")

    @ConstructorArgs(value = {
            @Arg(column = "currency_shortcut", javaType = Currency.class),
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
            @Arg(column = "currency", javaType = Currency.class),
            @Arg(column = "buy", javaType = Integer.class)
    })
    Set<PostgresRate> getExchangePointRates(@Param("exchangePointId") String exchangePointId);
}
