package cz.honestcity.endpoints.authority;

import cz.honestcity.service.rate.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private RateService rateService;

    @GetMapping("/rate")
    public GetCentralAuthorityRateResponse getCentralAuthorityRate(){
        return new GetCentralAuthorityRateResponse().setExchangeRate(rateService.getCentralAuthorityRate());
    }
}
