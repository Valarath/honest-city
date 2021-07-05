package cz.honestcity.service.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import cz.honestcity.service.vote.exchange.closed.UpVoteDeleteExchangePointService;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

//@Service(SuggestionServiceType.SuggestionServiceTypeNames.CLOSED_EXCHANGE_POINT)
@HonestCityService(beanId = ClosedExchangePointSuggestion.class)
public class ClosedExchangePointSuggestionService extends SuggestionService<ClosedExchangePointSuggestion> {

    private final ClosedExchangePointSuggestionGateway gateway;

    private final UpVoteDeleteExchangePointService upVoteDeleteExchangePointService;

    public ClosedExchangePointSuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway, ClosedExchangePointSuggestionGateway gateway, UpVoteDeleteExchangePointService upVoteDeleteExchangePointService) {
        super(suggestionGateway);
        this.gateway = gateway;
        this.upVoteDeleteExchangePointService = upVoteDeleteExchangePointService;
    }

    @Override
    public void suggest(List<ClosedExchangePointSuggestion> suggestions) {
        gateway.suggests(suggestions);
        suggesterVotesForHisSuggestions(suggestions, upVoteDeleteExchangePointService);
    }

    @Override
    public ClosedExchangePointSuggestion getSuggestion(String suggestionId) {
        return gateway.getSuggestion(suggestionId);
    }

    public List<ClosedExchangePointSuggestion> getScoredSuggestions(String exchangePointId) {
        return gateway.getExchangePointSuggestions(exchangePointId).stream()
                .sorted(this::compareUserScore)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClosedExchangePointSuggestion> getUserSuggestions(String userId) {
        return gateway.getUserSuggestions(userId);
    }
}
