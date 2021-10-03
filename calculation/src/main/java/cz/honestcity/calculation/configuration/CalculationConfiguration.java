package cz.honestcity.calculation.configuration;

import cz.honestcity.calculation.distance.HarvesinDistanceCalculator;
import cz.honestcity.calculation.vote.VoteCalculationService;
import cz.honestcity.service.distance.DistanceCalculatorGateway;
import cz.honestcity.service.distance.DistanceMetricGateway;
import cz.honestcity.service.vote.VoteCalculationGateway;
import cz.honestcity.service.vote.VoteCalculationMetricGateway;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author michal.keder
 */
@Configuration
public class CalculationConfiguration {

	@Bean
	@ConditionalOnMissingBean(DistanceCalculatorGateway.class)
	public DistanceCalculatorGateway getHarvesinDistanceCalculator(DistanceMetricGateway gateway) {
		return new HarvesinDistanceCalculator(gateway);
	}

	@Bean
	@ConditionalOnMissingBean(VoteCalculationGateway.class)
	public VoteCalculationGateway getVoteCalculationService(VoteCalculationMetricGateway gateway){
		return new VoteCalculationService(gateway);
	}
}
