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

	private static final double areaRange = 5.0;

	@Autowired
	private ExchangeGateway exchangeGateway;

	public void createExchange(Exchange newExchange) {
		exchangeGateway.createExchange(newExchange);
	}

	public List<Exchange> getExchangesInArea(Position userPosition){
		return exchangeGateway.getAllExchanges().stream()
				.filter(exchange -> isInArea(userPosition,exchange.getPosition()))
				.collect(Collectors.toList());
	}

	private boolean isInArea(Position userPosition, Position exchangePosition){
		return getDistanceFromUser(userPosition,exchangePosition)<=areaRange;
	}

	private double getDistanceFromUser(Position userPosition, Position exchangePosition){
		return Math.sqrt(getRoundedSumInPositions(userPosition,exchangePosition));
	}

	private double getRoundedSumInPositions(Position userPosition, Position exchangePosition){
		return Math.pow(calculateDifference(userPosition.getLatitude(),exchangePosition.getLatitude()),2)
				+ Math.pow(calculateDifference(userPosition.getLongitude(),exchangePosition.getLongitude()),2);
	}

	private double calculateDifference(double userPosition, double exchangePosition) {
		return userPosition-exchangePosition;
	}
}
