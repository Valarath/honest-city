package cz.honestcity.database.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.service.rate.RateDatabaseGateway;
import cz.honestcity.service.rate.RateGatewayType;
import org.springframework.stereotype.Service;

@Service(RateGatewayType.RateGatewayConstants.RATE_DATABASE_GATEWAY)
public class RatePostgresGateway implements RateDatabaseGateway {

    private final RatePostgresMapper ratePostgresMapper;

    public RatePostgresGateway(RatePostgresMapper ratePostgresMapper) {
        this.ratePostgresMapper = ratePostgresMapper;
    }

    @Override
    public void saveCentralAuthorityRate(ExchangeRate exchangeRate) {
        long exchangeRateId = ratePostgresMapper.saveExchangeRates();
        ratePostgresMapper.saveCentralAuthorityRate(exchangeRate.getRates(), exchangeRateId);
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
