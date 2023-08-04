package cz.honestcity.database.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.exchange.Watched;

import java.time.LocalDate;

public class ExchangePostgresRate extends ExchangeRate {

    public ExchangePostgresRate(String id, LocalDate watchedFrom, LocalDate watchedTo) {
        setId(id);
        //TODO zkus tohle upravit primo v maperu
        setWatched(new Watched().setFrom(watchedFrom).setTo(watchedTo));
    }
}
