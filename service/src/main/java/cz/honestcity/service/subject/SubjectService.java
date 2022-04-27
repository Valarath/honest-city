package cz.honestcity.service.subject;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.service.distance.DistanceCalculatorGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class SubjectService {

    protected DistanceCalculatorGateway distanceCalculator;

    public abstract void createSubject(WatchedSubject subject);

    public abstract List<? extends WatchedSubject> getSubjectsInArea(Position position);

    protected boolean isInArea(Position userPosition, Position exchangePosition) {
        return distanceCalculator.isInArea(userPosition, exchangePosition);
    }

    @Autowired
    public void setDistanceCalculator(DistanceCalculatorGateway distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }
}
