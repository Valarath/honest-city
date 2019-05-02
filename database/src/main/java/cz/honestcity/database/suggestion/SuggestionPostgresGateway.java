package cz.honestcity.database.suggestion;

import cz.honestcity.database.suggestion.exchange.closed.ClosedExchangePointSuggestionPostgresMapper;
import cz.honestcity.database.suggestion.exchange.create.NewExchangePointSuggestionPostgresMapper;
import cz.honestcity.database.suggestion.exchange.rate.ExchangeRatePostgresSuggestion;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.suggestion.SuggestionGateway;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import cz.honestcity.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("SuggestionPostgresGateway")
public class SuggestionPostgresGateway implements BaseSuggestionGateway {

    @Autowired
    protected SuggestionPostgresMapper suggestionPostgresMapper;

    @Autowired
    protected Map<String, VoteService> voteServices;

    @Override
    public void removeSuggestions(List<? extends Suggestion> toRemove) {
        suggestionPostgresMapper.removeSuggestions(toRemove);
    }

    @Override
    public void suggests(List<? extends Suggestion> suggestions) {

    }

    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return null;
    }

    @Override
    public List<? extends Suggestion> getUserSuggestions(long userId) {
        return null;
    }
}
