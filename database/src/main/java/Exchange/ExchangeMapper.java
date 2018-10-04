package Exchange;

import cz.honestcity.model.exchange.Exchange;

import java.util.List;

public interface ExchangeMapper {
	List<Exchange> getAllExchanges();

	void createNewExchange(Exchange newExchange);
}
