package cz.honestcity.service.suggestion;

public enum SuggestionServiceType {
    NEW_EXCHANGE_POINT(SuggestionServiceTypeNames.NEW_EXCHANGE_POINT),
    CLOSED_EXCHANGE_POINT(SuggestionServiceTypeNames.CLOSED_EXCHANGE_POINT),
    EXCHANGE_RATE_CHANGE(SuggestionServiceTypeNames.EXCHANGE_RATE_CHANGE),
    BASE_SERVICE(SuggestionServiceTypeNames.BASE_SERVICE);

    private String name;

    SuggestionServiceType(String name) {
        this.name = name;
    }

    public static class SuggestionServiceTypeNames{
        public final static String NEW_EXCHANGE_POINT="NewExchangePointSuggestionService";
        public final static String BASE_SERVICE="BaseSuggestionService";
        public final static String CLOSED_EXCHANGE_POINT="ClosedExchangePointSuggestionService";
        public final static String EXCHANGE_RATE_CHANGE="ExchangeRateChangeSuggestionService";
    }
}
