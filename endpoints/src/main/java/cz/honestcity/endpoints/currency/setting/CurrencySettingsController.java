package cz.honestcity.endpoints.currency.setting;

import cz.honestcity.model.currency.settings.CurrencySettings;
import cz.honestcity.service.currency.settings.CurrencySettingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author michal.keder
 */
@RestController
public class CurrencySettingsController {

    private final CurrencySettingsService currencySettingsService;

    public CurrencySettingsController(CurrencySettingsService currencySettingsService) {
        this.currencySettingsService = currencySettingsService;
    }

    @GetMapping(CurrencySettingsEndpointsUrl.GET_CURRENCY_SETTINGS)
    public GetCurrencySettingsResponse get(){
        return new GetCurrencySettingsResponse()
                .setCurrencySettings(getCurrencySettings());
    }

    private List<CurrencySettings> getCurrencySettings(){
        return currencySettingsService.get();
    }

}
