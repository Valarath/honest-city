package cz.honestcity.service.base;

import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.exchange.ExchangeRateValues;
import cz.honestcity.model.exchange.Rate;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.user.User;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractServiceTest {

    protected static final int SUGGESTION_ID = 1;
    protected static final long EXCHANGE_POINT_ID = 1;
    protected static final int VOTES_ON_ACCEPTED_SCENARIO = 20;
    protected static final long USER_ID = 1;
    protected static final int USER_SCORE = 0;
    protected static final long EXCHANGE_RATE_ID = 1;
    private static final int LATITUDE = 0;
    private static final int LONGITUDE = 0;
    private static final int HONEST_BUY_VALUE = 2;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    protected ClosedExchangePointSuggestion getClosedExchangePointSuggestionForTest() {
        return (ClosedExchangePointSuggestion) new ClosedExchangePointSuggestion()
                .setExchangePointId(EXCHANGE_POINT_ID)
                .setSuggestedBy(getUserForTest())
                .setId(SUGGESTION_ID);
    }

    protected User getUserForTest() {
        return new User()
                .setId(USER_ID)
                .setScore(USER_SCORE);
    }

    protected NewExchangePointSuggestion getNewExchangePointSuggestionForTest() {
        return (NewExchangePointSuggestion) new NewExchangePointSuggestion()
                .setPosition(getPositionForTest())
                .setSuggestedBy(getUserForTest())
                .setId(SUGGESTION_ID);
    }

    protected  <T> List<? extends T> getSuggestionsForTest(Class<T> clazz) {
        return new ArrayList<T>();
    }

    protected ExchangeRateSuggestion getExchangeRateSuggestionForTest() {
        return (ExchangeRateSuggestion) new ExchangeRateSuggestion()
                .setExchangePointId(EXCHANGE_POINT_ID)
                .setSuggestedExchangeRate(getExchangePointRateForTest())
                .setSuggestedBy(getUserForTest())
                .setId(SUGGESTION_ID);
    }

    protected Position getPositionForTest() {
        return new Position()
                .setLatitude(LATITUDE)
                .setLongitude(LONGITUDE);
    }

    protected ExchangeRate getExchangePointRateForTest() {
        return new ExchangeRate()
                .setRates(getHonestRatesForTest())
                .setId(EXCHANGE_RATE_ID);
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
}
