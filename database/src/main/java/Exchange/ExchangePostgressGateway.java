package Exchange;

import cz.honestcity.service.gateway.ExchangeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cz.honestcity.model.exchange.Exchange;

import java.util.List;

@Service
public class ExchangePostgressGateway implements ExchangeGateway {

	@Autowired
	private ExchangeMapper exchangeMapper;

	@Override
	public List<Exchange> getAllExchanges() {
		return exchangeMapper.getAllExchanges();
	}

	@Override
	public void createExchange(Exchange newExchange) {
		exchangeMapper.createNewExchange(newExchange);
	}
}
