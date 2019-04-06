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

    static class SuggestionServiceTypeNames{
        public final static String NEW_EXCHANGE_POINT="NewExchangePointService";
        public final static String BASE_SERVICE="BaseService";
        public final static String CLOSED_EXCHANGE_POINT="ClosedExchangePointService";
        public final static String EXCHANGE_RATE_CHANGE="ExchangeRateChangeService";
    }
}
