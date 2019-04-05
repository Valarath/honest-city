package cz.honestcity.database.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.exchange.Rate;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.Set;

@Mapper
public interface RatePostgresMapper {

    @Insert("INSERT INTO central_authority_rate(active_from, active_to)\n" +
            "values (#{exchangeRate.watched.from},#{exchangeRate.watched.to});")
    @Options(useGeneratedKeys = true, keyProperty = "exchangeRate.id", keyColumn = "exchange_rate_id")
    void saveCentralAuthorityRate(@Param("exchangeRate") ExchangeRate exchangeRate);

    void saveCentralAuthorityRate(@Param("rates") Set<Rate> rates);

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

    Set<Rate> getCentralAuthorityRates();

    Set<Rate> getExchangePointRates(long exchangePointId);
}
