package cz.honestcity.model.exchange;

import cz.honestcity.model.subject.ImmobileWatchedSubject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Exchange extends ImmobileWatchedSubject {
    private ExchangePointRate exchangePointRate;
}
