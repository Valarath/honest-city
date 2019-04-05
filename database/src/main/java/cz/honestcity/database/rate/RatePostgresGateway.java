package cz.honestcity.database.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.service.gateway.RateDatabaseGateway;
import cz.honestcity.service.gateway.RateGatewayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(RateGatewayType.RateGatewayConstants.RATE_DATABASE_GATEWAY)
public class RatePostgresGateway implements RateDatabaseGateway {

    @Autowired
    private RatePostgresMapper ratePostgresMapper;

    @Override
    public void saveCentralAuthorityRate(ExchangeRate exchangeRate) {
        ratePostgresMapper.saveCentralAuthorityRate(exchangeRate);
        ratePostgresMapper.saveCentralAuthorityRate(exchangeRate.getRates());
    }

    @Override
    public ExchangeRate getCentralAuthorityRate() {
        return ratePostgresMapper
                .getCentralAuthorityExchangeRate()
                .setRates(ratePostgresMapper.getCentralAuthorityRates());
    }

    @Override
    public ExchangeRate getExchangePointRate(long exchangePointId) {
        return ratePostgresMapper
                .getRate(exchangePointId)
                .setRates(ratePostgresMapper.getExchangePointRates(exchangePointId));
    }
}
