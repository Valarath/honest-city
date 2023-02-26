package cz.honestcity.service.rate;

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
        getRateDatabaseGateway().saveCentralAuthorityRate(getRateAuthorityGateway().getRate(LocalDate.now()));
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
        return (RateDatabaseGateway) rateGateways.get(RateDatabaseGateway.class.getSimpleName());
    }

    private RateAuthorityGateway getRateAuthorityGateway() {
        return (RateAuthorityGateway) rateGateways.get(RateAuthorityGateway.class.getSimpleName());
    }
}
