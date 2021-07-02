package cz.honestcity.service.currency.settings;

import cz.honestcity.model.currency.settings.CurrencySettings;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author michal.keder
 */
@Service
public class CurrencySettingsService {

    private final CurrencySettingsGateway currencySettingsGateway;

    public CurrencySettingsService(CurrencySettingsGateway currencySettingsGateway) {
        this.currencySettingsGateway = currencySettingsGateway;
    }

    public List<CurrencySettings> get(){
        return currencySettingsGateway.get();
    }

}
