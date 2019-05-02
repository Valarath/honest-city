package cz.honestcity.database.suggestion.exchange.create;

import cz.honestcity.database.suggestion.SuggestionPostgresGateway;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.suggestion.exchange.create.NewExchangePointSuggestionGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewExchangePointSuggestionPostgresGateway extends SuggestionPostgresGateway implements NewExchangePointSuggestionGateway {

    @Autowired
    private NewExchangePointSuggestionPostgresMapper mapper;

    @Override
    public void suggests(List<? extends Suggestion> suggestions) {
        suggestions.forEach(suggestion ->{
            suggestion.setId(suggestionPostgresMapper.suggest(suggestion));
            mapper.suggestsNewExchangePoint((NewExchangePointSuggestion) suggestion);
            voteServices.get(VoteType.VoteConstants.NEW_EXCHANGE_POINT).upVote(suggestion.getId(),suggestion.getSuggestedBy().getId());
        });
    }

    @Override
    public NewExchangePointSuggestion getSuggestion(long suggestionId) {
        return mapper.getNewExchangePointSuggestion(suggestionId);
    }

    @Override
    public List<? extends NewExchangePointSuggestion> getUserSuggestions(long userId) {
        return mapper.getUserNewExchangePointSuggestions(userId);
    }
}
