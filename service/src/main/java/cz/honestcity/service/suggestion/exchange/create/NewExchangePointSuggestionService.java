package cz.honestcity.service.suggestion.exchange.create;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.distance.DistanceCalculatorGateway;
import cz.honestcity.service.suggestion.NewSubjectSuggestionService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.vote.exchange.create.UpVoteNewExchangePointService;

import java.util.List;
import java.util.stream.Collectors;

@HonestCityService(beanId = NewExchangePointSuggestion.class)
public class NewExchangePointSuggestionService extends SuggestionService<NewExchangePointSuggestion> implements NewSubjectSuggestionService<NewExchangePointSuggestion> {

    private final DistanceCalculatorGateway distanceCalculator;
    private final NewExchangePointSuggestionGateway gateway;
    private final UpVoteNewExchangePointService upVoteNewExchangePointService;

    public NewExchangePointSuggestionService(DistanceCalculatorGateway distanceCalculator, NewExchangePointSuggestionGateway gateway, UpVoteNewExchangePointService upVoteNewExchangePointService) {
        this.distanceCalculator = distanceCalculator;
        this.gateway = gateway;
        this.upVoteNewExchangePointService = upVoteNewExchangePointService;
    }

    @Override
    public void suggest(List<NewExchangePointSuggestion> suggestions) {
        List<NewExchangePointSuggestion> suggestibleSuggestions = getSuggestibleSuggestions(suggestions, gateway);
        gateway.suggests(suggestibleSuggestions);
        suggesterVotesForHisSuggestions(suggestibleSuggestions, upVoteNewExchangePointService);
    }

    @Override
    public NewExchangePointSuggestion getSuggestion(String suggestionId) {
        return gateway.getSuggestion(suggestionId);
    }

    public List<NewExchangePointSuggestion> getScoredSuggestions(String exchangePointId) {
        return gateway.getExchangePointSuggestions(exchangePointId).stream()
                .sorted(this::compareUserScore)
                .map(this::setSuggestorLoginData)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewExchangePointSuggestion> getUserSuggestions(String userId) {
        return gateway.getUserSuggestions(userId);
    }

    @Override
    public void update(NewExchangePointSuggestion suggestion) {
        super.update(suggestion);
        gateway.update(suggestion);
    }

    @Override
    public List<NewExchangePointSuggestion> getAllInArea(Position position) {
        return gateway.getAll().stream()
                .filter(it -> it.getState() == State.IN_PROGRESS)
                .filter(it -> distanceCalculator.isInArea(position, it.getPosition()))
                .map(this::setSuggestorLoginData)
                .collect(Collectors.toList());
    }
}
