package cz.honestcity.database.currency.settings;

import cz.honestcity.model.currency.settings.CurrencySettings;
import cz.honestcity.service.currency.settings.CurrencySettingsGateway;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author michal.keder
 */
@Service
public class CurrencySettingsPostgresGateway implements CurrencySettingsGateway {

    private final CurrencySettingsPostgresMapper currencySettingsPostgresMapper;

    public CurrencySettingsPostgresGateway(CurrencySettingsPostgresMapper currencySettingsPostgresMapper) {
        this.currencySettingsPostgresMapper = currencySettingsPostgresMapper;
    }

    @Override
    public List<CurrencySettings> get() {
        return currencySettingsPostgresMapper.get();
    }
}
