package cz.honestcity.service.exchange;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.Position;
import cz.honestcity.service.gateway.ExchangeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeService {

	private static final double EARTH_RADIUS=6372.797560856;
	public static final int AREA_RANGE_IN_METERS = 22000;

	@Autowired
	private ExchangeGateway exchangeGateway;


	public void createExchange(ExchangePoint newExchangePoint) {
		exchangeGateway.createExchange(newExchangePoint);
	}

	public void changeExchangeRate( long newExchangeRateId, long exchangePointId) {
		exchangeGateway.changeExchangeRate(newExchangeRateId,exchangePointId);
	}

	public List<ExchangePoint> getExchangesInArea(Position userPosition){
		return exchangeGateway.getAllExchanges().stream()
				.filter(exchange -> isInArea(AREA_RANGE_IN_METERS,userPosition,exchange.getPosition()))
				.collect(Collectors.toList());
	}

	private boolean isInArea(int areaRangeInMeters,Position userPosition, Position exchangePosition){
		return calculateDistance(userPosition,exchangePosition)<= areaRangeInMeters;
	}

	private double getEarthRadiusInMeters(){
		return EARTH_RADIUS*1000;
	}

	private double calculateDistance(Position userPosition,Position exchangePosition){
		return getEarthRadiusInMeters()* calculateGreatDistanceCircle(calculateChordLength(userPosition,exchangePosition));
	}

	private double calculateGreatDistanceCircle(Double chordLength) {
		return 2 * Math.atan2(Math.sqrt(chordLength), Math.sqrt(1-chordLength));
	}

	private double calculateChordLength(Position userPosition,Position exchangePosition) {
		return calculateHaversin(userPosition.getLatitude(),exchangePosition.getLongitude()) +
				getCosOfPositionInRadians(userPosition) * getCosOfPositionInRadians(exchangePosition) *
						calculateHaversin(userPosition.getLongitude(),exchangePosition.getLongitude());
	}

	private double getCosOfPositionInRadians(Position position) {
		return Math.cos(toRadians(position.getLatitude()));
	}

	private double calculateHaversin(double position1, double position2){
		return Math.pow(Math.sin(getDifferenceInRad(position1,position2) / 2),2);
	}

	private double getDifferenceInRad(double value1,double value2){
		return toRadians(value1-value2);
	}

	private double toRadians(double value){
		return value * Math.PI / 180;
	}

	public void deleteExchangePoint(long exchangePointId) {

	}
}
