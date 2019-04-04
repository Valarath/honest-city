package cz.honestcity.database.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.service.gateway.RateDatabaseGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RatePossgresGateway implements RateDatabaseGateway {

    @Autowired
    private RatePostgresMapper ratePostgresMapper;

    @Override
    public ExchangeRate getRate(LocalDate day) {
        return ratePostgresMapper.getRate(day);
    }
}
