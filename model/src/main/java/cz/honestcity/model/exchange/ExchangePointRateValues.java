package cz.honestcity.model.exchange;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ExchangePointRateValues extends ExchangeRateValues{
    private double sell;
}
