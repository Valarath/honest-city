package bank;

import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.exchange.ExchangeRateValues;
import cz.honestcity.model.exchange.Rate;
import cz.honestcity.service.gateway.RateCrawlerGateway;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import static bank.BankCsvValuesPosition.CZECH_NATIONAL_BANK;

@Service
public class CzechNationalBankCrawler implements RateCrawlerGateway {
    private static final String SPLIT_BY="|";
    private static final String BASE_URL_FOR_NATIONAL_BANK_CRAWLING = "https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.txt";
    private static final int NUMBER_OF_LINES_TO_SKIP= 2;

    @Override
    public ExchangeRate getRate(LocalDate day) {
        try {
            return new ExchangeRate()
                    .setValidFor(day)
                    .setRates(convert(getRatesInCsv(day)));
        } catch (IOException ex) {
            throw new ProblemInCrawlingException();
        }
    }

    private BufferedReader getRatesInCsv(LocalDate day) throws IOException {
            return new BufferedReader(new InputStreamReader(new URL(getUrlForDayExchange(day)).openStream()));
    }

    private BufferedReader skipFirstLines(BufferedReader bufferedReader) throws IOException {
        for(int lineNumber = 1; lineNumber < NUMBER_OF_LINES_TO_SKIP; lineNumber++)
            bufferedReader.readLine();
        return bufferedReader;
    }

    private Set<Rate> convert(BufferedReader csvRates) throws IOException {
        var rates = new HashSet<Rate>();
        for(String line = csvRates.readLine(); line !=null;line=csvRates.readLine())
            rates.add(convert(line));
        return rates;
    }

    private Rate convert(String line){
        var values = line.split(SPLIT_BY);
        return new Rate()
                .setCurrency(getCurrency(values[CZECH_NATIONAL_BANK.getCurrencyCode()]))
                .setRateValues(getRateValues(values[CZECH_NATIONAL_BANK.getSell()]));
    }

    private ExchangeRateValues getRateValues(String value) {
        return new ExchangeRateValues()
                .setSell(Integer.parseInt(value));
    }

    private Currency getCurrency(String value) {
        return Currency.valueOf(value);
    }

    private String getUrlForDayExchange(LocalDate day){
        return new StringBuilder(BASE_URL_FOR_NATIONAL_BANK_CRAWLING)
                .append("?date=")
                .append(day.format(getDateFormat()))
                .toString();
    }

    private DateTimeFormatter getDateFormat(){
        return DateTimeFormatter.ofPattern("dd.mm.yyyy");
    }

    public static class ProblemInCrawlingException extends RuntimeException{}
}
