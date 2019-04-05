package cz.honestcity.database.exchange;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.model.subject.Position;

import java.time.LocalDate;

public class ExchangePostgresPoint extends ExchangePoint {

    public ExchangePostgresPoint(Double longitude, Double latitude, Long id, LocalDate watchedTo, HonestyStatus honestyStatus) {
        setPosition(new Position().setLongitude(longitude).setLatitude(latitude));
        setId(id);
        setWatchedTo(watchedTo);
        setHonestyStatus(honestyStatus);
    }
}
