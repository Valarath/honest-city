package cz.honestcity.service.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.vote.exchange.closed.UpVoteDeleteExchangePointService;

import java.util.List;
import java.util.stream.Collectors;

@HonestCityService(beanId = ClosedExchangePointSuggestion.class)
public class ClosedExchangePointSuggestionService extends SuggestionService<ClosedExchangePointSuggestion> {

    private final ClosedExchangePointSuggestionGateway gateway;

    private final UpVoteDeleteExchangePointService upVoteDeleteExchangePointService;

    public ClosedExchangePointSuggestionService(ClosedExchangePointSuggestionGateway gateway, UpVoteDeleteExchangePointService upVoteDeleteExchangePointService) {
        this.gateway = gateway;
        this.upVoteDeleteExchangePointService = upVoteDeleteExchangePointService;
    }

    @Override
    public void suggest(List<ClosedExchangePointSuggestion> suggestions) {
        List<ClosedExchangePointSuggestion> suggestibleSuggestions = getSuggestibleSuggestions(suggestions,gateway);
        gateway.suggests(suggestibleSuggestions);
        suggesterVotesForHisSuggestions(suggestibleSuggestions, upVoteDeleteExchangePointService);
    }

    @Override
    public ClosedExchangePointSuggestion getSuggestion(String suggestionId) {
        return gateway.getSuggestion(suggestionId);
    }

    @Override
    public List<ClosedExchangePointSuggestion> getScoredSuggestions(String exchangePointId) {
        return gateway.getExchangePointSuggestions(exchangePointId).stream()
                .sorted(this::compareUserScore)
                .map(this::setSuggestorLoginData)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClosedExchangePointSuggestion> getUserSuggestions(String userId) {
        return gateway.getUserSuggestions(userId);
    }
}
