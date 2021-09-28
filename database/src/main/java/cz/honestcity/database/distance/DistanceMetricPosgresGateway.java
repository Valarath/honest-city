package cz.honestcity.database.distance;

import cz.honestcity.model.distance.DistanceMetric;
import cz.honestcity.service.distance.DistanceMetricGateway;
import org.springframework.stereotype.Service;

/**
 * @author michal.keder
 */
@Service
public class DistanceMetricPosgresGateway implements DistanceMetricGateway {

	private final DistanceMetricPostgresMapper mapper;

	public DistanceMetricPosgresGateway(DistanceMetricPostgresMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public DistanceMetric get() {
		return mapper.get();
	}
}
