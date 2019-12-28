package cz.honestcity.endpoints.authority;

import cz.honestcity.service.rate.RateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    private final RateService rateService;

    public AuthorityController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/rate")
    public GetCentralAuthorityRateResponse getCentralAuthorityRate() {
        return new GetCentralAuthorityRateResponse().setExchangeRate(rateService.getCentralAuthorityRate());
    }
}
