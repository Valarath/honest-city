package cz.honestcity.model.suggestion;


import cz.honestcity.model.exchange.ExchangeRate;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExchangeRateSuggestion extends Suggestion {
    private ExchangeRate suggestedExchangeRate;
}
