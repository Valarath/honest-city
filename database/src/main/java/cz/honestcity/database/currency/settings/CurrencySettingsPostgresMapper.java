package cz.honestcity.database.currency.settings;

import cz.honestcity.model.currency.settings.CurrencySettings;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author michal.keder
 */
@Mapper
public interface CurrencySettingsPostgresMapper {

    @Select("SELECT currency_settings_id, main_country_currency, currency from currency_settings")
    @Results(value = {
            @Result(property = "id",column = "currency_settings_id"),
            @Result(property = "mainCountryCurrency",column = "main_country_currency"),
            @Result(property = "currency",column = "currency")
    })
    List<CurrencySettings> get();
}
