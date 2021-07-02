package cz.honestcity.endpoints.currency.setting;

import cz.honestcity.model.currency.settings.CurrencySettings;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author michal.keder
 */
@Data
@Accessors(chain = true)
public class GetCurrencySettingsResponse {

    private List<CurrencySettings> currencySettings;

}
