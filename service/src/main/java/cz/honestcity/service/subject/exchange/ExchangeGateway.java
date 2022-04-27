package cz.honestcity.service.subject.exchange;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.HonestyStatus;

import java.util.List;

public interface ExchangeGateway {

	List<ExchangePoint> getExchangePoints();

	void createExchange(ExchangePoint newExchangePoint);

	void deActivateOldExchangeRate(String exchangePointId);

    void changeExchangeRate(String newExchangeRateId, String exchangePointId);

    void addExchangeRateToExchangePoint(String newExchangeRateId, String exchangePointId);

    void deleteExchangePoint(String exchangePointId);

    void setHonestyStatus(String exchangePointId, HonestyStatus calculateHonestyStatus);

    ExchangePoint getExchangePoint(String exchangePointId);
}
