package cz.honestcity.endpoints.exchange;

import cz.honestcity.model.exchange.ExchangePoint;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostExchangeRequest {
	private ExchangePoint newExchangePoint;
}
