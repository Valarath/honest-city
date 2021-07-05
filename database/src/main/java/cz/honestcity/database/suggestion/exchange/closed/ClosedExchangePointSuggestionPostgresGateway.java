package cz.honestcity.database.suggestion.exchange.closed;

import cz.honestcity.database.suggestion.SuggestionPostgresGateway;
import cz.honestcity.database.suggestion.SuggestionPostgresMapper;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.suggestion.exchange.closed.ClosedExchangePointSuggestionGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClosedExchangePointSuggestionPostgresGateway implements ClosedExchangePointSuggestionGateway {

    private final ClosedExchangePointSuggestionPostgresMapper mapper;
    protected final SuggestionPostgresMapper suggestionPostgresMapper;

    public ClosedExchangePointSuggestionPostgresGateway(SuggestionPostgresMapper suggestionPostgresMapper, ClosedExchangePointSuggestionPostgresMapper mapper) {
        this.mapper = mapper;
        this.suggestionPostgresMapper = suggestionPostgresMapper;
    }

    @Override
    public List<ClosedExchangePointSuggestion> getExchangePointSuggestions(String exchangePointId) {
        return mapper.getExchangePointSuggestions(exchangePointId);
    }

    @Override
    public void suggests(List<ClosedExchangePointSuggestion> closedExchangePointSuggestions) {
        closedExchangePointSuggestions.forEach(suggestion -> {
            suggestionPostgresMapper.suggest(suggestion);
            mapper.reportNonExistingPoint( suggestion);
        });
    }

    @Override
    public ClosedExchangePointSuggestion getSuggestion(String suggestionId) {
        return mapper.getClosedExchangePointSuggestion(suggestionId);
    }

    @Override
    public List<ClosedExchangePointSuggestion> getUserSuggestions(String userId) {
        return mapper.getUserClosedExchangePointSuggestions(userId);
    }
}
