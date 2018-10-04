package cz.honestcity.service.exchange;

import cz.honestcity.model.exchange.Exchange;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostExchangeRequest {
	private Exchange newExchange;
}
