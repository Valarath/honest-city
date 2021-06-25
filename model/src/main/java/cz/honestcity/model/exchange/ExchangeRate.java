package cz.honestcity.model.exchange;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ExchangeRate {
    protected String id;
    protected Watched watched;
    protected Set<? extends Rate> rates;
}
