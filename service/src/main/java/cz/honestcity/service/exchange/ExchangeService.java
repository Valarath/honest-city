package cz.honestcity.service.exchange;

import cz.honestcity.model.exchange.Exchange;
import cz.honestcity.model.subject.Position;
import cz.honestcity.service.gateway.ExchangeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeService {

	private static final int areaRange = 5;

	@Autowired
	private ExchangeGateway exchangeGateway;

	public List<Exchange> getExchangesInArea(Position userPosition){
		return exchangeGateway.getAllExchanges().stream()
				.filter(exchange -> isInArea(userPosition,exchange.getPosition()))
				.collect(Collectors.toList());
	}

	private boolean isInArea(Position center, Position exchange){
		return true;
	}

	public void createExchange(Exchange newExchange) {
		exchangeGateway.createExchange(newExchange);
	}
}
