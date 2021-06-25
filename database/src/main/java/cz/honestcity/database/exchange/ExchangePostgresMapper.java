package cz.honestcity.database.exchange;

import cz.honestcity.model.exchange.ExchangePoint;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.model.suggestion.State;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

@Mapper
public interface ExchangePostgresMapper {

	@Select("SELECT exchange_point_id, active_to, longitude, longitude, honesty_status\n" +
			"FROM exchange_point;")
	@ConstructorArgs(value = {
			@Arg(column = "longitude",javaType = Double.class),
			@Arg(column = "latitude",javaType = Double.class),
			@Arg(column = "exchange_point_id",javaType = String.class),
			@Arg(column = "active_to",javaType = LocalDate.class),
			@Arg(column = "honesty_status",javaType = HonestyStatus.class, typeHandler = EnumTypeHandler.class)
	})
	List<ExchangePostgresPoint> getAllExchanges();

	@Insert("INSERT into exchange_point(honesty_status, latitude, longitude)\n" +
			"values (#{exchangePoint.honestyStatus},#{exchangePoint.latitude},#{exchangePoint.longitude});")
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
