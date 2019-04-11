package cz.honestcity.service.subject.exchange;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.service.rate.RateService;
import cz.honestcity.service.subject.SubjectService;
import cz.honestcity.service.subject.SubjectServiceType;
import cz.honestcity.service.suggestion.exchange.rate.ExchangeRateSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(SubjectServiceType.SubjectTypeConstants.EXCHANGE)
public class ExchangeService extends SubjectService {

	private static final double EARTH_RADIUS=6372.797560856;
	private static final int AREA_RANGE_IN_METERS = 22000;

	@Autowired
	private ExchangeGateway exchangeGateway;

	@Autowired
	private ExchangeRateSuggestionService suggestionService;

	@Autowired
	private RateService rateService;

	@Override
	public void createSubject(WatchedSubject subject) {
		exchangeGateway.createExchange((ExchangePoint) subject);
	}

	@Override
	public List<? extends WatchedSubject> getSubjectsInArea(Position position) {
		return exchangeGateway.getAllExchanges().stream()
				.filter(exchange -> isInArea(AREA_RANGE_IN_METERS,position,exchange.getPosition()))
				.map(this::getFullyInitializeExchangePoint)
				.collect(Collectors.toList());
	}

	public void changeExchangeRate( long newExchangeRateId, long exchangePointId) {
		exchangeGateway.deActivateOldExchangeRate(exchangePointId);
		exchangeGateway.changeExchangeRate(newExchangeRateId,exchangePointId);
	}

	private ExchangePoint getFullyInitializeExchangePoint(ExchangePoint exchangePoint) {
		return exchangePoint
				.setExchangePointRate(rateService.getExchangePointRate(exchangePoint.getId()))
				.setExchangeRateSuggestions(suggestionService.getScoredSuggestions(exchangePoint.getId()));
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
		exchangeGateway.deleteExchangePoint(exchangePointId);
	}


}
