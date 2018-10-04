package cz.honestcity.service.gateway;

import cz.honestcity.model.exchange.Exchange;

import java.util.List;

public interface ExchangeGateway {

	List<Exchange> getAllExchanges();

	void createExchange(Exchange newExchange);
}
