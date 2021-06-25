package cz.honestcity.database.exchange;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.service.subject.exchange.ExchangeGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangePostgresGateway implements ExchangeGateway {

	private final ExchangePostgresMapper exchangeMapper;

	public ExchangePostgresGateway(ExchangePostgresMapper exchangeMapper) {
		this.exchangeMapper = exchangeMapper;
	}

	@Override
	public List<? extends ExchangePoint> getAllExchanges() {
		return exchangeMapper.getAllExchanges();
	}

	@Override
	public void createExchange(ExchangePoint newExchangePoint) {
		exchangeMapper.createNewExchange(newExchangePoint);
	}

	@Override
	public void deActivateOldExchangeRate(String exchangePointId) {
		exchangeMapper.deActivateOldExchangeRate(exchangePointId);
	}

	@Override
	public void changeExchangeRate(String newExchangeRateId, String exchangePointId) {
		exchangeMapper.setNewExchangeRate(newExchangeRateId,exchangePointId);
	}

	@Override
	public void deleteExchangePoint(String exchangePointId) {
		exchangeMapper.deleteExchangePoint(exchangePointId);
	}

	@Override
	public void setHonestyStatus(String exchangePointId, HonestyStatus honestyStatus) {
		exchangeMapper.setHonestyStatus(exchangePointId,honestyStatus);
	}
}
