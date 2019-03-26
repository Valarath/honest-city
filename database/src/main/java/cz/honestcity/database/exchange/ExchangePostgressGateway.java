package cz.honestcity.database.exchange;

import cz.honestcity.service.gateway.ExchangeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cz.honestcity.model.exchange.ExchangePoint;

import java.util.List;

@Service
public class ExchangePostgressGateway implements ExchangeGateway {

	@Autowired
	private ExchangePostgressMapper exchangeMapper;

	@Override
	public List<ExchangePoint> getAllExchanges() {
		return exchangeMapper.getAllExchanges();
	}

	@Override
	public void createExchange(ExchangePoint newExchangePoint) {
		exchangeMapper.createNewExchange(newExchangePoint);
	}

	@Override
	public void changeExchangeRate(long newExchangeRateId, long exchangePointId) {
		exchangeMapper.changeExchangeRate(newExchangeRateId,exchangePointId);
	}
}
