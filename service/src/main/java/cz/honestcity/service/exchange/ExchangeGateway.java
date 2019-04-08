package cz.honestcity.service.exchange;

import cz.honestcity.model.exchange.ExchangePoint;

import java.util.List;

public interface ExchangeGateway {

	List<? extends ExchangePoint> getAllExchanges();

	void createExchange(ExchangePoint newExchangePoint);

	void deActivateOldExchangeRate(long exchangePointId);

    void changeExchangeRate(long newExchangeRateId, long exchangePointId);

    void deleteExchangePoint(long exchangePointId);
}
