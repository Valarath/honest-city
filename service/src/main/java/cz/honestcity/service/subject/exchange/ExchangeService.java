package cz.honestcity.service.subject.exchange;

import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.exchange.Rate;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.rate.RateService;
import cz.honestcity.service.subject.SubjectService;
import cz.honestcity.service.suggestion.exchange.rate.ExchangeRateSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

//@Service(SubjectServiceType.SubjectTypeConstants.EXCHANGE)
@HonestCityService(beanId = ExchangePoint.class)
public class ExchangeService extends SubjectService {

    private static final int AREA_RANGE_IN_METERS = 22000;

    private final ExchangeGateway exchangeGateway;

    private final ExchangeRateSuggestionService suggestionService;

    private final RateService rateService;

    @Autowired
    public ExchangeService(ExchangeGateway exchangeGateway, ExchangeRateSuggestionService suggestionService, RateService rateService) {
        this.exchangeGateway = exchangeGateway;
        this.suggestionService = suggestionService;
        this.rateService = rateService;
    }

    @Override
    public void createSubject(WatchedSubject subject) {
        exchangeGateway.createExchange((ExchangePoint) subject);
    }

    @Override
    public List<? extends WatchedSubject> getSubjectsInArea(Position position) {
        return exchangeGateway.getAllExchanges().stream()
                .filter(exchange -> isInArea(AREA_RANGE_IN_METERS, position, exchange.getPosition()))
                .map(this::getFullyInitializeExchangePoint)
                .collect(Collectors.toList());
    }

    public void changeExchangeRate(String newExchangeRateId, String exchangePointId) {
        exchangeGateway.deActivateOldExchangeRate(exchangePointId);
        exchangeGateway.changeExchangeRate(newExchangeRateId, exchangePointId);
        exchangeGateway.setHonestyStatus(exchangePointId,calculateHonestyStatus(exchangePointId));
    }

    private ExchangePoint getFullyInitializeExchangePoint(ExchangePoint exchangePoint) {
        return exchangePoint
                .setExchangePointRate(rateService.getExchangePointRate(exchangePoint.getId()))
                .setExchangeRateSuggestions(suggestionService.getScoredSuggestions(exchangePoint.getId()));
    }

    public void deleteExchangePoint(String exchangePointId) {
        exchangeGateway.deleteExchangePoint(exchangePointId);
    }

    private HonestyStatus calculateHonestyStatus(String exchangePointId) {
        double exchangePointEuroRate = getEurRate(rateService.getExchangePointRate(exchangePointId));
        double centralAuthorityEuroRate = getEurRate(rateService.getCentralAuthorityRate());
        if(1-exchangePointEuroRate/centralAuthorityEuroRate*100>10)
            return HonestyStatus.DISHONEST;
        else
            return HonestyStatus.DISHONEST;
    }


    private double getEurRate(ExchangeRate exchangeRate) {
        return exchangeRate.getRates().stream()
                .filter(this::isEuroRate)
                .collect(Collectors.toList())
                .get(0).getRateValues().getBuy();
    }

    private boolean isEuroRate(Rate rate) {
        return rate.getCurrency().equals(Currency.EU);
    }

}
