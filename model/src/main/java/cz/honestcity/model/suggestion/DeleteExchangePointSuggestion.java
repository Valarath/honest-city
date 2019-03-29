package cz.honestcity.model.suggestion;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class DeleteExchangePointSuggestion extends Suggestion {
    private long exchangePointId;
}
