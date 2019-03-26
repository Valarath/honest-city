package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostExchangeRateChangeRequest {
    private Suggestion suggestion;
    private long exchangePointId;
    private ExchangeRate suggestedExchangeRate;
}
