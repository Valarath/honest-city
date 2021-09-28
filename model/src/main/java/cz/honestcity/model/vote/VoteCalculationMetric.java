package cz.honestcity.model.vote;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VoteCalculationMetric {

	private double scoreModificator;

	private int lowestValueForAcceptance;

}
