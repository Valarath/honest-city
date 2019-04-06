package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(SuggestionServiceType.SuggestionServiceTypeNames.CLOSED_EXCHANGE_POINT)
public class NonExistingExchangePointService extends SuggestionService{
    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
        suggestionGateway.reportNonExistingPoint((List<NonExistingExchangePointSuggestion>) suggestions);
    }

    public NonExistingExchangePointSuggestion getNonExistingExchangePointSuggestion(long suggestionId){
        return suggestionGateway.getNonExistingExchangePointSuggestion(suggestionId);
    }
}
