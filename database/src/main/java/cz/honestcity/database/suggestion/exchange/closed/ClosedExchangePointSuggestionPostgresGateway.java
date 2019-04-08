package cz.honestcity.database.suggestion.exchange.closed;

import cz.honestcity.database.suggestion.SuggestionPostgresGateway;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.suggestion.exchange.closed.ClosedExchangePointSuggestionGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClosedExchangePointSuggestionPostgresGateway extends SuggestionPostgresGateway implements ClosedExchangePointSuggestionGateway {

    @Autowired
    private ClosedExchangePointSuggestionPostgresMapper mapper;

    @Override
    public void reportClosedPoint(List<ClosedExchangePointSuggestion> closedExchangePointSuggestions) {
        closedExchangePointSuggestions.forEach(suggestion ->{
            suggestion.setId(suggestionPostgresMapper.suggest(suggestion));
            mapper.reportNonExistingPoint(suggestion);
            voteServices.get(VoteType.VoteConstants.DELETE_EXCHANGE_POINT).upVote(suggestion.getId(),suggestion.getSuggestedBy().getId());
        });
    }

    @Override
    public ClosedExchangePointSuggestion getClosedExchangePointSuggestion(long suggestionId) {
        return mapper.getNonExistingExchangePointSuggestion(suggestionId);
    }
}
