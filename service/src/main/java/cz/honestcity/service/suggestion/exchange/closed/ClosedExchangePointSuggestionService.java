package cz.honestcity.service.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import cz.honestcity.service.suggestion.base.BaseSuggestionService;
import cz.honestcity.service.vote.exchange.closed.UpVoteDeleteExchangePointService;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

//@Service(SuggestionServiceType.SuggestionServiceTypeNames.CLOSED_EXCHANGE_POINT)
@HonestCityService(beanId = ClosedExchangePointSuggestion.class)
public class ClosedExchangePointSuggestionService extends BaseSuggestionService {

    private final ClosedExchangePointSuggestionGateway gateway;

    private final UpVoteDeleteExchangePointService upVoteDeleteExchangePointService;

    public ClosedExchangePointSuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway, ClosedExchangePointSuggestionGateway gateway, UpVoteDeleteExchangePointService upVoteDeleteExchangePointService) {
        super(suggestionGateway);
        this.gateway = gateway;
        this.upVoteDeleteExchangePointService = upVoteDeleteExchangePointService;
    }

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
        gateway.suggests(suggestions);
        suggesterVotesForHisSuggestions(suggestions, upVoteDeleteExchangePointService);
    }

    @Override
    public Suggestion getSuggestion(String suggestionId) {
        return gateway.getSuggestion(suggestionId);
    }

    @Override
    public List<? extends Suggestion> getUserSuggestions(String userId) {
        return gateway.getUserSuggestions(userId);
    }
}
