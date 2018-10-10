package cz.honestcity.endpoints.exchange;

import cz.honestcity.model.exchange.Exchange;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GetExchangesResponse {
	private List<Exchange> exchanges;
}
