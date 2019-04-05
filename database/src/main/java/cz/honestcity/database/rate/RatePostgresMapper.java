package cz.honestcity.database.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface RatePostgresMapper {
    ExchangeRate getRate(LocalDate day);

    void save(ExchangeRate exchangeRate);
}
