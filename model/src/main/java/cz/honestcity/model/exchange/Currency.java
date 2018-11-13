package cz.honestcity.model.exchange;

public enum Currency {
    EU,USD;
    public static boolean contains(String value){
        for (Currency currency:Currency.values())
            if(currency.name().equals(value))
                return true;
        return false;
    }
}
