package cz.honestcity.database.distance;

import cz.honestcity.model.distance.DistanceMetric;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author michal.keder
 */
@Mapper
public interface DistanceMetricPostgresMapper {

	@Select("SELECT area_range from distance_calculation_metric")
	@Results(value = {
			@Result(property = "areaRange", column = "area_range"),
	})
	DistanceMetric get();

}
