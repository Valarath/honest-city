package cz.honestcity.service.gateway;

import cz.honestcity.model.exchange.ExchangePoint;

import java.util.List;

public interface ExchangeGateway {

	List<ExchangePoint> getAllExchanges();

	void createExchange(ExchangePoint newExchangePoint);

    void changeExchangeRate(long newExchangeRateId, long exchangePointId);

    void deleteExchangePoint(long exchangePointId);
}
