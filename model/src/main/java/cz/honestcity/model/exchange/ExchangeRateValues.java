package cz.honestcity.model.exchange;

import cz.honestcity.model.HonestCitySerializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExchangeRateValues extends HonestCitySerializable {
    protected double buy;
}
