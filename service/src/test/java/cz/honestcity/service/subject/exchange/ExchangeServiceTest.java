package cz.honestcity.service.subject.exchange;

import cz.honestcity.model.exchange.*;
import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.service.base.AbstractServiceTest;
import cz.honestcity.service.rate.RateService;
import cz.honestcity.service.suggestion.exchange.rate.ExchangeRateSuggestionService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ExchangeServiceTest extends AbstractServiceTest {

    private static final int HONEST_BUY_VALUE = 2;
    private static final int DISHONEST_BUY_VALUE = 1;
    private static final int LATITUDE = 100;
    private static final int LONGITUDE = 100;

    @InjectMocks
    private ExchangeService service;

    @Mock
    private ExchangeGateway gateway;

    @Mock
    private ExchangeRateSuggestionService suggestionService;

    @Mock
    private RateService rateService;

    @Test
    public void createSubject() {
        ExchangePoint exchangePoint = getExchangePointForTest();
        prepareEnvironmentForCreateSubject(exchangePoint);
        service.createSubject(exchangePoint);
    }

    private void prepareEnvironmentForCreateSubject(ExchangePoint exchangePointForTest) {
        doNothing().when(gateway).createExchange(exchangePointForTest);
    }

    @Test
    public void getSubjectsInArea_zeroSubjectReturned() {
        prepareEnvironmentForGetSubjectsInAreaOneSubjectReturned(getExchangePointForTest());
        assertTrue(service.getSubjectsInArea(getPositionAwayFromSubject()).isEmpty());
    }

    private Position getPositionAwayFromSubject(){
        return new Position()
                .setLatitude(LATITUDE)
                .setLongitude(LONGITUDE);
    }

    @Test
    public void getSubjectsInArea_oneSubjectReturned() {
        ExchangePoint exchangePoint = getExchangePointForTest();
        prepareEnvironmentForGetSubjectsInAreaOneSubjectReturned(exchangePoint);
        assertEquals(exchangePoint,service.getSubjectsInArea(exchangePoint.getPosition()).get(0));
    }

    private void prepareEnvironmentForGetSubjectsInAreaOneSubjectReturned(ExchangePoint exchangePoint) {
        doReturn(Collections.singletonList(exchangePoint)).when(gateway).getAllExchanges();
        when(suggestionService.getScoredSuggestions(exchangePoint.getId())).thenReturn(exchangePoint.getExchangeRateSuggestions());
        when(rateService.getExchangePointRate(exchangePoint.getId())).thenReturn(exchangePoint.getExchangePointRate());
    }

    @Test
    public void changeExchangeRate_toHonest() {
        prepareHonestEnvironmentForChangeExchangeRate();
        service.changeExchangeRate(EXCHANGE_RATE_ID,EXCHANGE_POINT_ID);
    }

    private void prepareHonestEnvironmentForChangeExchangeRate() {
        when(rateService.getExchangePointRate(EXCHANGE_POINT_ID)).thenReturn(getExchangePointRateForTest());
        prepareCommonEnvironmentForChangeExchangeRate();
    }

    @Test
    public void changeExchangeRate_toDishonest() {
        prepareDishonestEnvironmentForChangeExchangeRate();
        service.changeExchangeRate(EXCHANGE_RATE_ID,EXCHANGE_POINT_ID);
    }

    private void prepareDishonestEnvironmentForChangeExchangeRate() {
        when(rateService.getExchangePointRate(EXCHANGE_POINT_ID)).thenReturn(getExchangePointRateForTest().setRates(getDishonestRatesForTest()));
        prepareCommonEnvironmentForChangeExchangeRate();
    }


    private void prepareCommonEnvironmentForChangeExchangeRate() {
        when(rateService.getCentralAuthorityRate()).thenReturn(getExchangePointRateForTest());
        when(rateService.getExchangePointRate(EXCHANGE_POINT_ID)).thenReturn(getExchangePointRateForTest());
        doNothing().when(gateway).deActivateOldExchangeRate(EXCHANGE_POINT_ID);
        doNothing().when(gateway).changeExchangeRate(EXCHANGE_RATE_ID,EXCHANGE_POINT_ID);
        doNothing().when(gateway).deActivateOldExchangeRate(EXCHANGE_POINT_ID);
    }

    @Test
    public void deleteExchangePoint() {
        prepareEnvironmentForDeleteExchangePointTest();
        service.deleteExchangePoint(EXCHANGE_POINT_ID);
    }

    private void prepareEnvironmentForDeleteExchangePointTest() {
        doNothing().when(gateway).deleteExchangePoint(EXCHANGE_POINT_ID);
    }


    private ExchangePoint getExchangePointForTest(){
        return (ExchangePoint) new ExchangePoint()
                .setExchangePointRate(getExchangePointRateForTest())
                .setExchangeRateSuggestions((List<ExchangeRateSuggestion>) getSuggestionsForTest(ExchangeRateSuggestion.class))
                .setPosition(getPositionForTest());
    }

    private ExchangeRate getExchangePointRateForTest() {
        return new ExchangeRate()
                .setRates(getHonestRatesForTest());
    }

    private Set<? extends Rate> getHonestRatesForTest() {
        HashSet<Rate> rates = new HashSet<>();
        rates.add(getHonestEuroRateForTest());
        return rates;
    }

    private Rate getHonestEuroRateForTest(){
        return new Rate()
                .setCurrency(Currency.EU)
                .setRateValues(getHonestRateValuesForTest());
    }

    private ExchangeRateValues getHonestRateValuesForTest() {
        return new ExchangeRateValues()
                .setBuy(HONEST_BUY_VALUE);
    }

    private Set<? extends Rate> getDishonestRatesForTest() {
        HashSet<Rate> rates = new HashSet<>();
        rates.add(getDishonestEuroRateForTest());
        return rates;
    }

    private Rate getDishonestEuroRateForTest(){
        return new Rate()
                .setCurrency(Currency.EU)
                .setRateValues(getDishonestRateValuesForTest());
    }


    private ExchangeRateValues getDishonestRateValuesForTest() {
        return new ExchangeRateValues()
                .setBuy(DISHONEST_BUY_VALUE);
    }
}