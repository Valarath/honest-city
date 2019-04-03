package cz.honestcity.model.suggestion;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class NonExistingExchangePointSuggestion extends Suggestion {
    private long exchangePointId;
}
