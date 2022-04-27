package cz.honestcity.calculation.distance;

import cz.honestcity.model.subject.Position;
import cz.honestcity.service.distance.DistanceCalculatorGateway;
import cz.honestcity.service.distance.DistanceMetricGateway;

/**
 * @author michal.keder
 */
public class HarvesinDistanceCalculator implements DistanceCalculatorGateway {

	private static final double EARTH_RADIUS=6372.797560856;

	private final DistanceMetricGateway distanceMetricGateway;

	public HarvesinDistanceCalculator(DistanceMetricGateway distanceMetricGateway) {
		this.distanceMetricGateway = distanceMetricGateway;
	}

	@Override
	public boolean isInArea(Position userPosition, Position exchangePosition){
		return getDistance(userPosition,exchangePosition) <= distanceMetricGateway.get().getAreaRange();
	}

	private double getEarthRadiusInMeters(){
		return EARTH_RADIUS*1000;
	}

	private Double getDistance(Position userPosition,Position exchangePosition){
		String distance = calculateDistance(userPosition, exchangePosition).toString();
		if(distance.length() > 8)
			distance = distance.substring(0,9);
		return Double.parseDouble(distance);
	}

	private Double calculateDistance(Position userPosition,Position exchangePosition){
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
}
