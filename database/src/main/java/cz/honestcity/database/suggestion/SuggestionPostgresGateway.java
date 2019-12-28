package cz.honestcity.database.suggestion;

import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import cz.honestcity.service.vote.VoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("SuggestionPostgresGateway")
public class SuggestionPostgresGateway implements BaseSuggestionGateway {

    protected final SuggestionPostgresMapper suggestionPostgresMapper;

    protected final Map<String, VoteService> voteServices;

    public SuggestionPostgresGateway(SuggestionPostgresMapper suggestionPostgresMapper, Map<String, VoteService> voteServices) {
        this.suggestionPostgresMapper = suggestionPostgresMapper;
        this.voteServices = voteServices;
    }

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
