package cz.honestcity.database.exchange;

import cz.honestcity.model.exchange.ExchangePoint;

import java.util.List;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExchangePostgresMapper {
	@Select("")
	List<ExchangePoint> getAllExchanges();

	@Select("")
	void createNewExchange(ExchangePoint newExchangePoint);

	@Update("")
	void changeExchangeRate(long newExchangeRateId, long exchangePointId);

    void deleteExchangePoint(long exchangePointId);
}
