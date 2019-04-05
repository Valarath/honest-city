package cz.honestcity.service.rate;

import cz.honestcity.service.gateway.RateDatabaseGateway;
import cz.honestcity.service.gateway.RateGateway;
import cz.honestcity.service.gateway.RateGatewayType;
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
    public void getRate() {
        ((RateDatabaseGateway) rateGateways.get(RateGatewayType.RATE_DATABASE_GATEWAY.name)).save(
                rateGateways.get(RateGatewayType.RATE_CRAWLING_GATEWAY.name).getRate(LocalDate.now()));
    }
}
