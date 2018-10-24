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

	private static final double AREA_RANGE = 500;
	private static final double EARTH_RADIUS=6372.797560856;

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
		return calculateDistance(userPosition,exchangePosition)<=AREA_RANGE;
	}

	private double getEarthRadiusInMeters(){
		return EARTH_RADIUS*1000;
	}

	private double calculateDistance(Position userPosition,Position exchangePosition){
		return getEarthRadiusInMeters()* calculateGreatDistanceCircle(calculateChordLength(userPosition,exchangePosition));
	}

	private double calculateGreatDistanceCircle(Double a) {
		return 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	}

	private double calculateChordLength(Position userPosition,Position exchangePosition) {
		return calculate(userPosition.getLatitude(),exchangePosition.getLongitude()) +
				getCosOfPositionInRadians(userPosition) * getCosOfPositionInRadians(exchangePosition) *
						calculate(userPosition.getLongitude(),exchangePosition.getLongitude());
	}

	private double getCosOfPositionInRadians(Position exchangePosition) {
		return Math.cos(toRadians(exchangePosition.getLatitude()));
	}

	private double calculate(double position1,double position2){
		return Math.pow(Math.sin(getDifferenceInRad(position1,position2) / 2),2);
	}

	private double getDifferenceInRad(double value1,double value2){
		return toRadians(value1-value2);
	}

	private double toRadians(double value){
		return value * Math.PI / 180;
	}
}
