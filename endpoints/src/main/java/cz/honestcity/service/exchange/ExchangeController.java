package cz.honestcity.service.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

	@Autowired
	private ExchangeService exchangeService;

	@GetMapping("/exchanges")
	public GetExchangesResponse getExchangesInArea(GetExchangesRequest request){
		return new GetExchangesResponse()
				.setExchanges(exchangeService.getExchangesInArea(request.getUserPosition()));
	}

	public void createExchange(PostExchangeRequest request){
		exchangeService.createExchange(request.getNewExchange());
	}
}
