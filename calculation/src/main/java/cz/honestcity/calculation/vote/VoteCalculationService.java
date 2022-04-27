package cz.honestcity.calculation.vote;

import cz.honestcity.model.vote.VoteCalculationMetric;
import cz.honestcity.service.vote.VoteCalculationGateway;
import cz.honestcity.service.vote.VoteCalculationMetricGateway;

/**
 * @author michal.keder
 */
public class VoteCalculationService implements VoteCalculationGateway {

	private final VoteCalculationMetricGateway voteCalculationMetricGateway;

	public VoteCalculationService(VoteCalculationMetricGateway voteCalculationMetricGateway) {
		this.voteCalculationMetricGateway = voteCalculationMetricGateway;
	}

	@Override
	public boolean isAcceptable(int userScore, int suggestionVotes) {
		VoteCalculationMetric calculationData = getCalculationData();
		return suggestionVotes * calculateUserTrustworthiness(userScore, calculationData) > calculationData.getLowestValueForAcceptance();
	}

	private double calculateUserTrustworthiness(int userScore, VoteCalculationMetric calculationData) {
		return Math.atan(userScore) + calculationData.getScoreModificator();
	}

	private VoteCalculationMetric getCalculationData() {
		return voteCalculationMetricGateway.get();
	}

}
