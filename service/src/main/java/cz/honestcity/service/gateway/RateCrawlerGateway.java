package cz.honestcity.service.gateway;

import cz.honestcity.model.exchange.ExchangeRate;

import java.time.LocalDate;

public interface RateCrawlerGateway extends RateGateway{
    ExchangeRate getRate(LocalDate day);
}
