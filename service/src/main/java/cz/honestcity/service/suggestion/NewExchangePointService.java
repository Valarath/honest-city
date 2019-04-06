package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(SuggestionServiceType.SuggestionServiceTypeNames.NEW_EXCHANGE_POINT)
public class NewExchangePointService extends SuggestionService {

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
        suggestionGateway.suggestsNewExchangePoint((List<NewExchangePointSuggestion>)suggestions);
    }

    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return suggestionGateway.getNewExchangePointSuggestion(suggestionId);
    }
}
