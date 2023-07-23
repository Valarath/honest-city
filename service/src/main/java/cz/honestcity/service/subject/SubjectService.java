package cz.honestcity.service.subject;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.distance.DistanceCalculatorGateway;
import cz.honestcity.service.rate.RateService;
import cz.honestcity.service.subject.exchange.HonestyStatusCalculationGateway;
import cz.honestcity.service.suggestion.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class SubjectService {

    protected DistanceCalculatorGateway distanceCalculator;
    protected  Map<String, SuggestionService<? extends Suggestion>> suggestionServices;
    protected  RateService rateService;
    protected  HonestyStatusCalculationGateway honestyStatusCalculationGateway;

    public abstract void createSubject(WatchedSubject subject);

    public abstract List<? extends WatchedSubject> getSubjectsInArea(Position position);

    protected boolean isInArea(Position userPosition, Position exchangePosition) {
        return distanceCalculator.isInArea(userPosition, exchangePosition);
    }

    protected List<Suggestion> getAllSuggestions(String subjectId, List<String> suggestionTypes) {
        return suggestionServices.entrySet().stream()
                .filter(it -> suggestionTypes.contains(it.getKey()))
                .map(Map.Entry::getValue)
                .map(it -> it.getScoredSuggestions(subjectId))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Autowired
    public void setSuggestionServices(Map<String, SuggestionService<? extends Suggestion>> suggestionServices) {
        this.suggestionServices = suggestionServices;
    }

    @Autowired
    public void setDistanceCalculator(DistanceCalculatorGateway distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    @Autowired
    public void setRateService(RateService rateService) {
        this.rateService = rateService;
    }

    @Autowired
    public void setHonestyStatusCalculationGateway(HonestyStatusCalculationGateway honestyStatusCalculationGateway) {
        this.honestyStatusCalculationGateway = honestyStatusCalculationGateway;
    }
}
