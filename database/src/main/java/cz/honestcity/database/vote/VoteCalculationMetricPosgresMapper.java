package cz.honestcity.database.vote;

import cz.honestcity.model.vote.VoteCalculationMetric;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VoteCalculationMetricPosgresMapper {

	@Select("SELECT score_modificator, lowest_value_for_acceptance from vote_calculation_metric")
	@Results(value = {
			@Result(property = "scoreModificator", column = "score_modificator"),
			@Result(property = "lowestValueForAcceptance", column = "lowest_value_for_acceptance"),
	})
	VoteCalculationMetric get();
}
