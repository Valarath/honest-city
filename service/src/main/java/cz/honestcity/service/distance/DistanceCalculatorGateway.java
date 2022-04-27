package cz.honestcity.service.distance;

import cz.honestcity.model.subject.Position;

/**
 * @author michal.keder
 */
public interface DistanceCalculatorGateway {

	boolean isInArea(Position userPosition, Position exchangePosition);
}
