package cz.honestcity.service.subject;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.subject.WatchedSubject;

import java.util.List;

public abstract class SubjectService {
    private static final double EARTH_RADIUS=6372.797560856;

    public abstract void createSubject(WatchedSubject subject);

    public abstract List<? extends WatchedSubject> getSubjectsInArea(Position position);

    protected boolean isInArea(int areaRangeInMeters,Position userPosition, Position exchangePosition){
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
}
