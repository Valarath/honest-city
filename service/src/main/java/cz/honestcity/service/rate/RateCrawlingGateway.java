package cz.honestcity.service.rate;

import cz.honestcity.model.exchange.ExchangeRate;

import java.time.LocalDate;

public interface RateCrawlingGateway extends RateGateway{
    ExchangeRate getRate(LocalDate day);
}
