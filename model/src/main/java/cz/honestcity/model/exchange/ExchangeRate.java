package cz.honestcity.model.exchange;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ExchangeRate {
    protected LocalDate validFor;
    protected Set<Rate> rates;
}
