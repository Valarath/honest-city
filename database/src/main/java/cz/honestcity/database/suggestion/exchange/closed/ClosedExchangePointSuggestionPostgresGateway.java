package cz.honestcity.database.suggestion.exchange.closed;

import cz.honestcity.database.suggestion.SuggestionPostgresGateway;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.suggestion.exchange.closed.ClosedExchangePointSuggestionGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClosedExchangePointSuggestionPostgresGateway extends SuggestionPostgresGateway implements ClosedExchangePointSuggestionGateway {

    private final ClosedExchangePointSuggestionPostgresMapper mapper;

    public ClosedExchangePointSuggestionPostgresGateway(ClosedExchangePointSuggestionPostgresMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void suggests(List<? extends Suggestion> closedExchangePointSuggestions) {
        closedExchangePointSuggestions.forEach(suggestion -> {
            suggestion.setId(suggestionPostgresMapper.suggest(suggestion));
            mapper.reportNonExistingPoint((ClosedExchangePointSuggestion) suggestion);
            voteServices.get(VoteType.VoteConstants.DELETE_EXCHANGE_POINT).upVote(suggestion.getId(), suggestion.getSuggestedBy().getId());
        });
    }

    @Override
    public ClosedExchangePointSuggestion getSuggestion(long suggestionId) {
        return mapper.getClosedExchangePointSuggestion(suggestionId);
    }

    @Override
    public List<? extends ClosedExchangePointSuggestion> getUserSuggestions(long userId) {
        return mapper.getUserClosedExchangePointSuggestions(userId);
    }
}
