package cz.honestcity.service.subject;

public enum SubjectType {
    EXCHANGE(SubjectTypeConstants.EXCHANGE);
    private String name;

    SubjectType(String name) {
        this.name = name;
    }

    public static class SubjectTypeConstants{
        public static final String EXCHANGE= "Exchange";
    }
}
