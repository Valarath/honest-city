package cz.honestcity.service.suggestion.exchange.create;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
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
        gateway.suggests(suggestions);
        suggesterVotesForHisSuggestions(suggestions, upVoteNewExchangePointService);
    }

    @Override
    public NewExchangePointSuggestion getSuggestion(String suggestionId) {
        return gateway.getSuggestion(suggestionId);
    }

    public List<NewExchangePointSuggestion> getScoredSuggestions(String exchangePointId) {
        return gateway.getExchangePointSuggestions(exchangePointId).stream()
                .sorted(this::compareUserScore)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewExchangePointSuggestion> getUserSuggestions(String userId) {
        return gateway.getUserSuggestions(userId);
    }

    @Override
    public List<NewExchangePointSuggestion> getAllInArea(Position position) {
        return gateway.getAll().stream()
                .filter(exchange -> DistanceCalculator.isInArea(position, exchange.getPosition()))
                .map(this::setSuggestorLoginData)
                .collect(Collectors.toList());
    }
}
