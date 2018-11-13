package cz.honestcity.database.exchange;

import cz.honestcity.model.exchange.Exchange;

import java.util.List;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExchangeMapper {
	@Select("")
	List<Exchange> getAllExchanges();

	@Select("")
	void createNewExchange(Exchange newExchange);
}
