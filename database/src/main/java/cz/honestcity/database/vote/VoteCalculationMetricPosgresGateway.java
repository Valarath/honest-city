package cz.honestcity.database.vote;

import cz.honestcity.model.vote.VoteCalculationMetric;
import cz.honestcity.service.vote.VoteCalculationMetricGateway;
import org.springframework.stereotype.Service;

@Service
public class VoteCalculationMetricPosgresGateway implements VoteCalculationMetricGateway {

	private final VoteCalculationMetricPosgresMapper mapper;

	public VoteCalculationMetricPosgresGateway(VoteCalculationMetricPosgresMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public VoteCalculationMetric get() {
		return mapper.get();
	}
}
