package cz.honestcity.model.vote;

public enum VoteType {
    NEW_EXCHANGE_POINT(VoteConstants.NEW_EXCHANGE_POINT),
    DELETE_EXCHANGE_POINT(VoteConstants.DELETE_EXCHANGE_POINT),
    EXCHANGE_RATE_CHANGE(VoteConstants.EXCHANGE_RATE_CHANGE);

    VoteType(String name) {
        this.name = name;
    }

    public  String name;

    public static class VoteConstants{
        public static final String NEW_EXCHANGE_POINT = "NewExchangePointVote";
        public static final String DELETE_EXCHANGE_POINT = "DeleteExchangePointVote";
        public static final String EXCHANGE_RATE_CHANGE = "ExchangeRateChangeVote";
    }
}
