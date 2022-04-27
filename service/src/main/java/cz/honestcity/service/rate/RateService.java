package cz.honestcity.service.rate;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import cz.honestcity.model.exchange.ExchangeRate;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class RateService {

    private final Map<String, ? extends RateGateway> rateGateways;

    public RateService(Map<String, ? extends RateGateway> rateGateways) {
        this.rateGateways = rateGateways;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "0 0 3 * * ?")
    public void crawlRate() {
        getAuthorityRate();
    }

    private void getAuthorityRate() {
        getRateDatabaseGateway().saveCentralAuthorityRate(getRateCrawlerGateway().getRate(LocalDate.now()));
    }

    public ExchangeRate getExchangePointRate(String exchangePointId) {
        return getRateDatabaseGateway().getExchangePointRate(exchangePointId);
    }

    public void saveSuggestionRate(ExchangeRate exchangeRate){
        getRateDatabaseGateway().saveSubjectRate(exchangeRate);
    }

    public ExchangeRate getCentralAuthorityRate() {
        return getRateDatabaseGateway().getCentralAuthorityRate();
    }

    private RateDatabaseGateway getRateDatabaseGateway() {
        return (RateDatabaseGateway) rateGateways.get(RateGatewayType.RATE_DATABASE_GATEWAY.name);
    }

    private RateCrawlingGateway getRateCrawlerGateway() {
        return (RateCrawlingGateway) rateGateways.get(RateGatewayType.RATE_CRAWLING_GATEWAY.name);
    }
}
