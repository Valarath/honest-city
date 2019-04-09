package cz.honestcity.service.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.suggestion.BaseSuggestionService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(SuggestionServiceType.SuggestionServiceTypeNames.CLOSED_EXCHANGE_POINT)
public class ClosedExchangePointSuggestionService extends BaseSuggestionService {

    @Autowired
    private ClosedExchangePointSuggestionGateway gateway;

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
        gateway.reportClosedPoint((List<ClosedExchangePointSuggestion>) suggestions);
    }

    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return gateway.getClosedExchangePointSuggestion(suggestionId);
    }

    @Override
    public List<? extends Suggestion> getUserSuggestions(long userId) {
        return gateway.getUserSuggestions(userId);
    }
 }
