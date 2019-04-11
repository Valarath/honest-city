package cz.honestcity.service.subject;

public enum SubjectServiceType {
    EXCHANGE(SubjectTypeConstants.EXCHANGE);
    private String name;

    SubjectServiceType(String name) {
        this.name = name;
    }

    public static class SubjectTypeConstants{
        public static final String EXCHANGE= "ExchangeService";
    }
}
