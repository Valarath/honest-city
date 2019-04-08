package cz.honestcity.database.rate;

import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.exchange.ExchangePointRateValues;
import cz.honestcity.model.exchange.Rate;

public class PostgresRate extends Rate {

    public PostgresRate(Currency currency, Integer buy) {
        setCurrency(currency);
        setRateValues(new ExchangePointRateValues().setBuy(buy));
    }
}
