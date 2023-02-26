package cz.honestcity.database.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.configuration.IdProvider;
import cz.honestcity.service.rate.RateDatabaseGateway;

import java.time.LocalDate;

@HonestCityService(beanId = RateDatabaseGateway.class)
public class RatePostgresGateway implements RateDatabaseGateway {

    private final RatePostgresMapper ratePostgresMapper;
    private final IdProvider idProvider;

    public RatePostgresGateway(RatePostgresMapper ratePostgresMapper, IdProvider idProvider) {
        this.ratePostgresMapper = ratePostgresMapper;
        this.idProvider = idProvider;
    }

    @Override
    public void saveCentralAuthorityRate(ExchangeRate exchangeRate) {
        String exchangeRatesId = idProvider.provideNewId();
        ratePostgresMapper.saveExchangeRates(exchangeRatesId);
        saveCentralAuthorityRate(exchangeRate, exchangeRatesId);
        ratePostgresMapper.saveRates(exchangeRatesId, exchangeRate);
    }

    @Override
    public void saveSubjectRate(ExchangeRate exchangeRate) {
        ratePostgresMapper.saveExchangeRates(exchangeRate.getId());
        ratePostgresMapper.saveRates(exchangeRate.getId(), exchangeRate);
    }

    private void saveCentralAuthorityRate(ExchangeRate exchangeRate, String exchangeRatesId) {
        ratePostgresMapper.disableCentralAuthorityRate(LocalDate.now());
        ratePostgresMapper.saveCentralAuthorityRate(exchangeRatesId, exchangeRate);
    }

    @Override
    public ExchangeRate getCentralAuthorityRate() {
        return ratePostgresMapper
                .getCentralAuthorityExchangeRate()
                .setRates(ratePostgresMapper.getCentralAuthorityRates());
    }

    @Override
    public ExchangeRate getExchangePointRate(String exchangePointId) {
        ExchangePostgresRate rate = ratePostgresMapper.getRate(exchangePointId);
        return rate == null ? null : rate.setRates(ratePostgresMapper.getExchangePointRates(exchangePointId));
    }
}
