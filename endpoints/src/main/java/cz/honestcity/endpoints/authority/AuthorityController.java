package cz.honestcity.endpoints.authority;

import cz.honestcity.endpoints.BaseController;
import cz.honestcity.service.rate.RateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorityController extends BaseController {

    private final RateService rateService;

    public AuthorityController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping(AuthorityEndpointsUrl.GET_RATE)
    public GetCentralAuthorityRateResponse getCentralAuthorityRate() {
        return new GetCentralAuthorityRateResponse().setExchangeRate(rateService.getCentralAuthorityRate());
    }
}
