package cz.honestcity.service.rate;

public enum RateGatewayType {
    RATE_CRAWLING_GATEWAY(RateGatewayConstants.RATE_CRAWLING_GATEWAY),
    RATE_DATABASE_GATEWAY(RateGatewayConstants.RATE_DATABASE_GATEWAY);

    RateGatewayType(String name) {
        this.name = name;
    }

    public String name;

    public static class RateGatewayConstants{
        public static final String RATE_CRAWLING_GATEWAY ="RateCrawlingGateway";
        public static final String RATE_DATABASE_GATEWAY ="RateDatabaseGateway";
    }
}
