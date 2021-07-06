package cz.honestcity.service.suggestion.exchange.create;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.DistanceCalculator;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.NewSubjectSuggestionService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import cz.honestcity.service.vote.exchange.create.UpVoteNewExchangePointService;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

//@Service(SuggestionServiceType.SuggestionServiceTypeNames.NEW_EXCHANGE_POINT)
@HonestCityService(beanId = NewExchangePointSuggestion.class)
public class NewExchangePointSuggestionService extends SuggestionService<NewExchangePointSuggestion> implements NewSubjectSuggestionService<NewExchangePointSuggestion> {

    private final NewExchangePointSuggestionGateway gateway;
    private final UpVoteNewExchangePointService upVoteNewExchangePointService;

    public NewExchangePointSuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway, NewExchangePointSuggestionGateway gateway, UpVoteNewExchangePointService upVoteNewExchangePointService) {
        super(suggestionGateway);
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
                .filter(it -> DistanceCalculator.isInArea(position, it.getPosition()))
                .map(this::setSuggestorLoginData)
                .collect(Collectors.toList());
    }
}
