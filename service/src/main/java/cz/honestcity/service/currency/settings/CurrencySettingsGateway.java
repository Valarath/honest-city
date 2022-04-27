package cz.honestcity.service.currency.settings;

import cz.honestcity.model.currency.settings.CurrencySettings;

import java.util.List;

/**
 * @author michal.keder
 */
public interface CurrencySettingsGateway {

    List<CurrencySettings> get();

}
