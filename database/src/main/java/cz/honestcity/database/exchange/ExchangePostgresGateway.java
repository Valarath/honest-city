package cz.honestcity.database.exchange;

import cz.honestcity.service.gateway.ExchangeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cz.honestcity.model.exchange.ExchangePoint;

import java.util.List;

@Service
public class ExchangePostgresGateway implements ExchangeGateway {

	@Autowired
	private ExchangePostgresMapper exchangeMapper;

	@Override
	public List<ExchangePoint> getAllExchanges() {
		return exchangeMapper.getAllExchanges();
	}

	@Override
	public void createExchange(ExchangePoint newExchangePoint) {
		exchangeMapper.createNewExchange(newExchangePoint);
	}

	@Override
	public void deActivateOldExchangeRate(long exchangePointId) {
		exchangeMapper.deActivateOldExchangeRate(exchangePointId);
	}

	@Override
	public void changeExchangeRate(long newExchangeRateId, long exchangePointId) {
		exchangeMapper.setNewExchangeRate(newExchangeRateId,exchangePointId);
	}

	@Override
	public void deleteExchangePoint(long exchangePointId) {
		exchangeMapper.deleteExchangePoint(exchangePointId);
	}
}
