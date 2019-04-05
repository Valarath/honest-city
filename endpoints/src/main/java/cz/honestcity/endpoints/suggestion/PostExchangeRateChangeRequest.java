package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PostExchangeRateChangeRequest {
    private List<ExchangeRateSuggestion> exchangeRateSuggestions;
}
