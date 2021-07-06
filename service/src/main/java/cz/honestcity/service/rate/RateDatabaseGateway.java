package cz.honestcity.service.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;

public interface RateDatabaseGateway extends RateGateway {

    void saveCentralAuthorityRate(ExchangeRate exchangeRate);

    void saveSubjectRate(ExchangeRate exchangeRate);

    ExchangeRate getCentralAuthorityRate();

    ExchangeRate getExchangePointRate(String exchangePointId);


}
