package cz.honestcity.service.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class RateService {

    @Autowired
    private Map<String, ? extends RateGateway> rateGateways;

    @Scheduled(cron = "0 0 3 * * ?")
    public void crawlRate() {
        getRateDatabaseGateway().saveCentralAuthorityRate(getRateCrawlerGateway().getRate(LocalDate.now()));
    }
    public ExchangeRate getExchangePointRate(long exchangePointId){
        return getRateDatabaseGateway().getExchangePointRate(exchangePointId);
    }

    public ExchangeRate getCentralAuthorityRate(){
        return getRateDatabaseGateway().getCentralAuthorityRate();
    }

    private RateDatabaseGateway getRateDatabaseGateway() {
        return (RateDatabaseGateway) rateGateways.get(RateGatewayType.RATE_DATABASE_GATEWAY.name);
    }

    private RateCrawlerGateway getRateCrawlerGateway() {
        return (RateCrawlerGateway) rateGateways.get(RateGatewayType.RATE_CRAWLING_GATEWAY.name);
    }
}
