package cz.honestcity.database.suggestion.exchange.create;

import cz.honestcity.database.suggestion.SuggestionPostgresGateway;
import cz.honestcity.database.suggestion.SuggestionPostgresMapper;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.service.suggestion.exchange.create.NewExchangePointSuggestionGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewExchangePointSuggestionPostgresGateway implements NewExchangePointSuggestionGateway {

    private final NewExchangePointSuggestionPostgresMapper mapper;
    protected final SuggestionPostgresMapper suggestionPostgresMapper;

    public NewExchangePointSuggestionPostgresGateway(SuggestionPostgresMapper suggestionPostgresMapper, NewExchangePointSuggestionPostgresMapper mapper) {
        this.mapper = mapper;
        this.suggestionPostgresMapper = suggestionPostgresMapper;
    }

    @Override
    public List<NewExchangePointSuggestion> getExchangePointSuggestions(String exchangePointId) {
        return mapper.getExchangePointSuggestions(exchangePointId);
    }

    @Override
    public void suggests(List<NewExchangePointSuggestion> suggestions) {
        suggestions.forEach(suggestion -> {
            suggestionPostgresMapper.suggest(suggestion);
            mapper.suggestsNewExchangePoint(suggestion);
        });
    }

    @Override
    public NewExchangePointSuggestion getSuggestion(String suggestionId) {
        return mapper.getNewExchangePointSuggestion(suggestionId);
    }

    @Override
    public List<NewExchangePointSuggestion> getUserSuggestions(String userId) {
        return mapper.getUserNewExchangePointSuggestions(userId);
    }

    @Override
    public List<NewExchangePointSuggestion> getAll() {
        return mapper.getAllNewExchangePointSuggestions();
    }

    @Override
    public void update(NewExchangePointSuggestion suggestion) {
        mapper.update(suggestion);
    }
}
