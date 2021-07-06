package cz.honestcity.service.subject.exchange;

import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.exchange.Rate;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.subject.WatchedSubject;
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

//@Service(SubjectServiceType.SubjectTypeConstants.EXCHANGE)
@HonestCityService(beanId = ExchangePoint.class)
public class ExchangePointService extends SubjectService {

    private final ExchangeGateway exchangeGateway;

    private final Map<String, SuggestionService<? extends Suggestion>> suggestionServices;

    private final RateService rateService;

    @Autowired
    public ExchangePointService(ExchangeGateway exchangeGateway, Map<String, SuggestionService<? extends Suggestion>> suggestionServices, RateService rateService) {
        this.exchangeGateway = exchangeGateway;
        this.suggestionServices = suggestionServices;
        this.rateService = rateService;
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
        return suggestionServices.values().stream()
                .map(it -> it.getScoredSuggestions(exchangePoint.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void deleteExchangePoint(String exchangePointId) {
        exchangeGateway.deleteExchangePoint(exchangePointId);
    }

    private HonestyStatus calculateHonestyStatus(ExchangePoint exchangePoint) {
        Rate exchangePointEuroRate = getComparingRate(rateService.getExchangePointRate(exchangePoint.getId()));
        Rate centralAuthorityEuroRate = getComparingRate(rateService.getCentralAuthorityRate());
        if (isRateHonest(centralAuthorityEuroRate, exchangePointEuroRate))
            return HonestyStatus.DISHONEST;
        else
            return getHonestStatus(exchangePoint);
    }

    private HonestyStatus getHonestStatus(ExchangePoint exchangePoint) {
        HonestyStatus nextLevelOfHonesty = exchangePoint.getHonestyStatus().getNextLevelOfHonesty();
        return nextLevelOfHonesty == null ? HonestyStatus.HONEST : nextLevelOfHonesty;
    }

    private boolean isRateHonest(Rate centralAuthorityRate, Rate exchangePointRate) {
        return exchangePointRate == null || (1 - exchangePointRate.getRateValues().getBuy() / centralAuthorityRate.getRateValues().getBuy() * 100 > 10);
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

}
