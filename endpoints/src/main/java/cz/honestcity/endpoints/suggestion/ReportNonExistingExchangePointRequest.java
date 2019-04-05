package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ReportNonExistingExchangePointRequest {
    private List<NonExistingExchangePointSuggestion> nonExistingExchangePointSuggestions;
}
