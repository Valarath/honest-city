package cz.honestcity.endpoints.exchange;

import cz.honestcity.service.exchange.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/exchange")
public class ExchangeController {

	@Autowired
	private ExchangeService exchangeService;

	@GetMapping("/exchanges-in-area")
	public GetExchangesResponse getExchangesInArea(GetExchangesRequest request){
		return new GetExchangesResponse()
				.setExchangePoints(exchangeService.getExchangesInArea(request.getUserPosition(),request.getUserFilter()));
	}
}
