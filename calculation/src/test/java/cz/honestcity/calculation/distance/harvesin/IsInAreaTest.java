package cz.honestcity.calculation.distance.harvesin;

import cz.honestcity.calculation.distance.HarvesinDistanceCalculator;
import cz.honestcity.model.distance.DistanceMetric;
import cz.honestcity.model.subject.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author michal.keder
 */
public class IsInAreaTest {

	public static final int AREA_RANGE = 2200;
	public static final double SUBJECT_LATITUDE = 37.421998333333335;
	public static final double SUBJECT_LONGITUDE = -122.084;
	public static final int OUTSIDE_SUBJECT_RANGE_LATITUDE = 25;
	public static final int OUTSIDE_SUBJECT_LONGITUDE = 28;

	private HarvesinDistanceCalculator calculator;

	@BeforeEach
	public void before(){
		calculator = new HarvesinDistanceCalculator(this::getDistanceMetric);
	}

	@Test
	public void isInSubjectAreaTest(){
		boolean isInArea = calculator.isInArea(getUserPositionInSubjectArea(), getExchangePointPosition());
		Assertions.assertTrue(isInArea);
	}

	@Test
	public void isNotInSubjectAreaTest(){
		boolean isInArea = calculator.isInArea(getUserPositionOutsideSubjectArea(), getExchangePointPosition());
		Assertions.assertFalse(isInArea);
	}

	private DistanceMetric getDistanceMetric(){
		return new DistanceMetric()
				.setAreaRange(AREA_RANGE);
	}

	private Position getExchangePointPosition(){
		return new Position()
				.setLatitude(SUBJECT_LATITUDE)
				.setLongitude(SUBJECT_LONGITUDE);
	}

	private Position getUserPositionInSubjectArea(){
		return new Position()
				.setLatitude(SUBJECT_LATITUDE)
				.setLongitude(SUBJECT_LONGITUDE);
	}

	private Position getUserPositionOutsideSubjectArea(){
		return new Position()
				.setLatitude(OUTSIDE_SUBJECT_RANGE_LATITUDE)
				.setLongitude(OUTSIDE_SUBJECT_LONGITUDE);
	}
}
