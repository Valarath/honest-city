package cz.honestcity.database.exchange;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.HonestyStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExchangePointMapper {

	String TO_EXCHANGE_POINT ="toExchangePoint";

	@Select("SELECT exchange_point_id, active_to, longitude, latitude, honesty_status \n" +
			"FROM exchange_point;")
	@Results(id = TO_EXCHANGE_POINT,value = {
			@Result(property = "id",column = "exchange_point_id"),
			@Result(property = "honestyStatus",column = "honesty_status"),
			@Result(property = "watchedTo",column = "active_to"),
			@Result(property = "position.longitude",column = "longitude"),
			@Result(property = "position.latitude",column = "latitude")
	})
	List<ExchangePoint> getExchangePoints();

	@Insert("INSERT into exchange_point(exchange_point_id, honesty_status, latitude, longitude)\n" +
			"values (#{exchangePoint.id},#{exchangePoint.honestyStatus},#{exchangePoint.position.latitude},#{exchangePoint.position.longitude});")
	void createNewExchange(@Param("exchangePoint") ExchangePoint newExchangePoint);

	@Update("UPDATE exchange_point_rate\n" +
			"SET exchange_point_id = #{exchangePointId},\n" +
			"    exchange_rate_id = #{newExchangeRateId},\n" +
			"    active_from = now()::date\n" +
			"WHERE exchange_rate_id = #{newExchangeRateId}\n" +
			"  and exchange_point_id = #{exchangePointId};")
	void setNewExchangeRate(@Param("newExchangeRateId") String newExchangeRateId, @Param("exchangePointId") String exchangePointId);

	@Update("UPDATE exchange_point_rate\n" +
			"SET active_to = now()::date\n" +
			"WHERE exchange_point_id = #{exchangePointId};")
	void deActivateOldExchangeRate(@Param("exchangePointId") String exchangePointId);

	@Update("UPDATE exchange_point\n" +
			"SET active_to = now()::date\n" +
			"WHERE exchange_point_id = #{exchangePointId};")
    void deleteExchangePoint(@Param("exchangePointId") String exchangePointId);

	@Update("UPDATE exchange_point\n" +
			"SET honesty_status = #{honestyStatus}\n" +
			"WHERE exchange_point_id = #{exchangePointId};")
    void setHonestyStatus(@Param("exchangePointId") String exchangePointId, @Param("honestyStatus") HonestyStatus honestyStatus);
}
