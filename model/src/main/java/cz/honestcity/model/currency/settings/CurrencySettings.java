package cz.honestcity.model.currency.settings;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author michal.keder
 */
@Data
@Accessors(chain = true)
public class CurrencySettings {

    private String id;
    private Boolean mainCountryCurrency;
    private String currency;

}
