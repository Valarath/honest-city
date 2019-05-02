package cz.honestcity.service.base;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.user.User;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractServiceTest {

    protected static final int SUGGESTION_ID = 1;
    protected static final long EXCHANGE_POINT_ID = 1;
    protected static final int VOTES_ON_ACCEPTED_SCENARIO = 20;
    protected static final long USER_ID = 1;
    protected static final int USER_SCORE = 0;
    protected static final long EXCHANGE_RATE_ID = 1;
    public static final int LATITUDE = 0;
    public static final int LONGITUDE = 0;

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
                .setSuggestedExchangeRate(getSuggestedExchangeRate())
                .setSuggestedBy(getUserForTest())
                .setId(SUGGESTION_ID);
    }

    private ExchangeRate getSuggestedExchangeRate() {
        return new ExchangeRate()
                .setId(EXCHANGE_RATE_ID);
    }

    protected Position getPositionForTest() {
        return new Position()
                .setLatitude(LATITUDE)
                .setLongitude(LONGITUDE);
    }
}
