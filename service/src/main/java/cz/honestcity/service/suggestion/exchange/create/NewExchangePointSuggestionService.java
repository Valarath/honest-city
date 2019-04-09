package cz.honestcity.service.suggestion.exchange.create;

import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.suggestion.BaseSuggestionService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(SuggestionServiceType.SuggestionServiceTypeNames.NEW_EXCHANGE_POINT)
public class NewExchangePointSuggestionService extends BaseSuggestionService {

    @Autowired
    private NewExchangePointSuggestionGateway gateway;

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
        gateway.suggestsNewExchangePoint((List<NewExchangePointSuggestion>)suggestions);
    }

    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return gateway.getNewExchangePointSuggestion(suggestionId);
    }

    @Override
    public List<? extends Suggestion> getUserSuggestions(long userId) {
        return gateway.getUserSuggestions(userId);
    }
}
