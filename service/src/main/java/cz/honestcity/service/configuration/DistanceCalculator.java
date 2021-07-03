package cz.honestcity.service.configuration;

import cz.honestcity.model.subject.Position;

/**
 * @author michal.keder
 */
public class DistanceCalculator {

    private static final int AREA_RANGE_IN_METERS = 22000;
    private static final double EARTH_RADIUS=6372.797560856;

    public static boolean isInArea(Position userPosition, Position exchangePosition){
        return getDistance(userPosition,exchangePosition)<= AREA_RANGE_IN_METERS;
    }

    private static double getEarthRadiusInMeters(){
        return EARTH_RADIUS*1000;
    }

    private static Double getDistance(Position userPosition,Position exchangePosition){
        String distance = calculateDistance(userPosition, exchangePosition).toString();
        if(distance.length() > 8)
            distance = distance.substring(0,9);
        return Double.parseDouble(distance);
    }

    private static Double calculateDistance(Position userPosition,Position exchangePosition){
        return getEarthRadiusInMeters()* calculateGreatDistanceCircle(calculateChordLength(userPosition,exchangePosition));
    }

    private static double calculateGreatDistanceCircle(Double chordLength) {
        return 2 * Math.atan2(Math.sqrt(chordLength), Math.sqrt(1-chordLength));
    }

    private static double calculateChordLength(Position userPosition,Position exchangePosition) {
        return calculateHaversin(userPosition.getLatitude(),exchangePosition.getLongitude()) +
                getCosOfPositionInRadians(userPosition) * getCosOfPositionInRadians(exchangePosition) *
                        calculateHaversin(userPosition.getLongitude(),exchangePosition.getLongitude());
    }

    private static double getCosOfPositionInRadians(Position position) {
        return Math.cos(toRadians(position.getLatitude()));
    }

    private static double calculateHaversin(double position1, double position2){
        return Math.pow(Math.sin(getDifferenceInRad(position1,position2) / 2),2);
    }

    private static double getDifferenceInRad(double value1,double value2){
        return toRadians(value1-value2);
    }

    private static double toRadians(double value){
        return value * Math.PI / 180;
    }

}
