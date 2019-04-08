package cz.honestcity.service.rate;

import cz.honestcity.model.exchange.ExchangeRate;

public interface RateDatabaseGateway extends RateGateway {

    void saveCentralAuthorityRate(ExchangeRate exchangeRate);

    ExchangeRate getCentralAuthorityRate();

    ExchangeRate getExchangePointRate(long exchangePointId);


}
