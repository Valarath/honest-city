package cz.honestcity.service.suggestion.exchange.create;

import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import cz.honestcity.service.suggestion.base.BaseSuggestionService;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

//@Service(SuggestionServiceType.SuggestionServiceTypeNames.NEW_EXCHANGE_POINT)
@HonestCityService(beanId = NewExchangePointSuggestion.class)
public class NewExchangePointSuggestionService extends BaseSuggestionService {

    private final NewExchangePointSuggestionGateway gateway;

    public NewExchangePointSuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway, NewExchangePointSuggestionGateway gateway) {
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
