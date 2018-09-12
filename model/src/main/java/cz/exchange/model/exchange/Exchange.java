package cz.exchange.model.exchange;

import cz.exchange.model.subject.ImmobileWatchedSubject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Exchange extends ImmobileWatchedSubject {
    private ExchangeRate exchangeRate;
}
