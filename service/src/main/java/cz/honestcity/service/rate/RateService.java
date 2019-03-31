package cz.honestcity.service.rate;

import cz.honestcity.service.gateway.RateGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class RateService {

	@Autowired
	private Map<String,RateGateway> rateGateways;

	@Scheduled(cron = "0 0 3 * * ?")
	public void getRate(){
		//rateGateway.getRate(LocalDate.now());
	}
}
