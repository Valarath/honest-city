package cz.honestcity.model.currency.settings;

import cz.honestcity.model.HonestCitySerializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author michal.keder
 */
@Data
@Accessors(chain = true)
public class CurrencySettings extends HonestCitySerializable {

    private String id;
    private Boolean mainCountryCurrency;
    private String currency;

}
