package cz.honestcity.crawling.bank;

public enum BankCsvValuesPosition {
    CZECH_NATIONAL_BANK(4,5);

    BankCsvValuesPosition(int currencyCode, int sell) {
        this.currencyCode = currencyCode;
        this.sell = sell;
    }

    private int currencyCode;
    private int sell;

    public int getCurrencyCode() {
        return currencyCode -1;
    }

    public int getSell() {
        return sell -1;
    }
}
