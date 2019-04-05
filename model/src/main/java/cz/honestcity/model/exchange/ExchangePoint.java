package cz.honestcity.model.exchange;

import cz.honestcity.model.subject.ImmobileWatchedSubject;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ExchangePoint extends ImmobileWatchedSubject {
    private ExchangeRate exchangePointRate;
    private List<ExchangeRateSuggestion> exchangeRateSuggestions;
}
