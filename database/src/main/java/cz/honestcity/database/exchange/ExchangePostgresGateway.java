package cz.honestcity.database.exchange;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.service.subject.exchange.ExchangeGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangePostgresGateway implements ExchangeGateway {

	private final ExchangePointMapper exchangePointMapper;

	public ExchangePostgresGateway(ExchangePointMapper exchangePointMapper) {
		this.exchangePointMapper = exchangePointMapper;
	}

	@Override
	public List<ExchangePoint> getExchangePoints() {
		return exchangePointMapper.getExchangePoints();
	}

	@Override
	public void createExchange(ExchangePoint newExchangePoint) {
		exchangePointMapper.createNewExchange(newExchangePoint);
	}

	@Override
	public void deActivateOldExchangeRate(String exchangePointId) {
		exchangePointMapper.deActivateOldExchangeRate(exchangePointId);
	}

	@Override
	public void changeExchangeRate(String newExchangeRateId, String exchangePointId) {
		exchangePointMapper.setNewExchangeRate(newExchangeRateId,exchangePointId);
	}

	@Override
	public void deleteExchangePoint(String exchangePointId) {
		exchangePointMapper.deleteExchangePoint(exchangePointId);
	}

	@Override
	public void setHonestyStatus(String exchangePointId, HonestyStatus honestyStatus) {
		exchangePointMapper.setHonestyStatus(exchangePointId,honestyStatus);
	}
}
