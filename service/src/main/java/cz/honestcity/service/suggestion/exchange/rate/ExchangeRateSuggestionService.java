package cz.honestcity.service.suggestion.exchange.rate;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.login.LoginDataService;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import cz.honestcity.service.suggestion.base.BaseSuggestionService;
import cz.honestcity.service.vote.exchange.rate.UpVoteExchangePointRateChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.stream.Collectors;

//@Service(SuggestionServiceType.SuggestionServiceTypeNames.EXCHANGE_RATE_CHANGE)
@HonestCityService(beanId = ExchangeRateSuggestion.class)
public class ExchangeRateSuggestionService extends BaseSuggestionService<ExchangeRateSuggestion> {

    private final ExchangeRateSuggestionGateway gateway;

    private UpVoteExchangePointRateChangeService upVoteExchangePointRateChangeService;

    public ExchangeRateSuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway, ExchangeRateSuggestionGateway gateway) {
        super(suggestionGateway);
        this.gateway = gateway;
    }

    @Lazy
    @Autowired
    public void setUpVoteExchangePointRateChangeService(UpVoteExchangePointRateChangeService upVoteExchangePointRateChangeService) {
        this.upVoteExchangePointRateChangeService = upVoteExchangePointRateChangeService;
    }

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
        gateway.suggests(suggestions);
        suggesterVotesForHisSuggestions(suggestions, upVoteExchangePointRateChangeService);
    }

    @Override
    public Suggestion getSuggestion(String suggestionId) {
        return gateway.getSuggestion(suggestionId);
    }

    public List<ExchangeRateSuggestion> getScoredSuggestions(String exchangePointId) {
        return gateway.getExchangePointSuggestions(exchangePointId).stream()
                .sorted(this::compareUserScore)
                .collect(Collectors.toList());
    }

    private int compareUserScore(Suggestion userSuggestion1, Suggestion userSuggestion2) {
        return Integer.compare(userSuggestion1.getSuggestedBy().getScore(), userSuggestion2.getSuggestedBy().getScore());
    }

    @Override
    public List<? extends Suggestion> getUserSuggestions(String userId) {
        return gateway.getUserSuggestions(userId);
    }
}
