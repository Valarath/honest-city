package cz.honestcity.service.subject.exchange;

import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.exchange.Rate;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.rate.RateService;
import cz.honestcity.service.subject.SubjectService;
import cz.honestcity.service.suggestion.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@HonestCityService(beanId = ExchangePoint.class)
public class ExchangePointService extends SubjectService {

    private final ExchangeGateway exchangeGateway;

    @Autowired
    public ExchangePointService(ExchangeGateway exchangeGateway) {
        this.exchangeGateway = exchangeGateway;
    }

    @Override
    public void createSubject(WatchedSubject subject) {
        exchangeGateway.createExchange((ExchangePoint) subject);
    }

    @Override
    public List<? extends WatchedSubject> getSubjectsInArea(Position position) {
        return exchangeGateway.getExchangePoints().stream()
                .filter(exchange -> isInArea(position, exchange.getPosition()))
                .map(this::getFullyInitializeExchangePoint)
                .collect(Collectors.toList());
    }

    public void changeExchangeRate(String newExchangeRateId, String exchangePointId) {
        ExchangePoint exchangePoint = exchangeGateway.getExchangePoint(exchangePointId);
        exchangeGateway.deActivateOldExchangeRate(exchangePointId);
        changeExchangePointRate(newExchangeRateId, exchangePointId);
        exchangeGateway.setHonestyStatus(exchangePointId, calculateHonestyStatus(exchangePoint));
    }

    private HonestyStatus calculateHonestyStatus(ExchangePoint exchangePoint) {
        Rate exchangePointUsdRate = getComparingRate(rateService.getExchangePointRate(exchangePoint.getId()));
        Rate centralAuthorityUsdRate = getComparingRate(rateService.getCentralAuthorityRate());
        return honestyStatusCalculationGateway.calculate(exchangePointUsdRate, centralAuthorityUsdRate, exchangePoint.getHonestyStatus());
    }

    private Rate getComparingRate(ExchangeRate exchangeRate) {
        return exchangeRate == null ? null : getUsdRate(exchangeRate);
    }

    private Rate getUsdRate(ExchangeRate exchangeRate) {
        return exchangeRate.getRates().stream()
                .filter(this::isUsdRate)
                .findAny()
                .orElse(null);
    }

    private boolean isUsdRate(Rate rate) {
        return rate.getCurrency().equals(Currency.USD);
    }

    private void changeExchangePointRate(String newExchangeRateId, String exchangePointId) {
        ExchangeRate exchangePointRate = rateService.getExchangePointRate(exchangePointId);
        if (exchangePointRate == null)
            exchangeGateway.addExchangeRateToExchangePoint(newExchangeRateId, exchangePointId);
        else
            exchangeGateway.changeExchangeRate(newExchangeRateId, exchangePointId);
    }

    private ExchangePoint getFullyInitializeExchangePoint(ExchangePoint exchangePoint) {
        return exchangePoint
                .setExchangePointRate(rateService.getExchangePointRate(exchangePoint.getId()))
                .setSuggestions(getAllSuggestions(exchangePoint));
    }

    private List<Suggestion> getAllSuggestions(ExchangePoint exchangePoint) {
        return super.getAllSuggestions(
                exchangePoint.getId(),
                List.of(NewExchangePointSuggestion.class.getName(),
                        ExchangeRateSuggestion.class.getName(),
                        ClosedExchangePointSuggestion.class.getName()));
    }

    public void deleteExchangePoint(String exchangePointId) {
        exchangeGateway.deleteExchangePoint(exchangePointId);
    }

}
