package cz.honestcity.service.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import cz.honestcity.service.suggestion.base.BaseSuggestionService;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

//@Service(SuggestionServiceType.SuggestionServiceTypeNames.CLOSED_EXCHANGE_POINT)
@HonestCityService(beanId = ClosedExchangePointSuggestion.class)
public class ClosedExchangePointSuggestionService extends BaseSuggestionService {

    private final ClosedExchangePointSuggestionGateway gateway;

    public ClosedExchangePointSuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway, ClosedExchangePointSuggestionGateway gateway) {
        super(suggestionGateway);
        this.gateway = gateway;
    }

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
        gateway.suggests(suggestions);
    }

    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return gateway.getSuggestion(suggestionId);
    }

    @Override
    public List<? extends Suggestion> getUserSuggestions(long userId) {
        return gateway.getUserSuggestions(userId);
    }
}
