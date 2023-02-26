package cz.honestcity.service.rate;

import cz.honestcity.model.exchange.ExchangeRate;

import java.time.LocalDate;

public interface RateAuthorityGateway extends RateGateway{
    ExchangeRate getRate(LocalDate day);
}
