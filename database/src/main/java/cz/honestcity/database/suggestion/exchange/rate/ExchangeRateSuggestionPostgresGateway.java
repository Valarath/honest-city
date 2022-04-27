package cz.honestcity.database.suggestion.exchange.rate;

import cz.honestcity.database.suggestion.SuggestionPostgresMapper;
import cz.honestcity.model.exchange.Watched;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.service.suggestion.exchange.rate.ExchangeRateSuggestionGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExchangeRateSuggestionPostgresGateway implements ExchangeRateSuggestionGateway {

    private final ExchangeRateSuggestionPostgresMapper mapper;
    protected final SuggestionPostgresMapper suggestionPostgresMapper;

    public ExchangeRateSuggestionPostgresGateway(SuggestionPostgresMapper suggestionPostgresMapper, ExchangeRateSuggestionPostgresMapper mapper) {
        this.mapper = mapper;
        this.suggestionPostgresMapper = suggestionPostgresMapper;
    }

    @Override
    public List<ExchangeRateSuggestion> getExchangePointSuggestions(String exchangePointId) {
        List<ExchangeRateSuggestion> suggestions = mapper.getExchangePointSuggestions(exchangePointId);
        suggestions.forEach(this::setRates);
        return suggestions;
    }

    @Override
    public void suggests(List<ExchangeRateSuggestion> suggestions) {
        suggestions.forEach(suggestion -> {
            suggestionPostgresMapper.suggest(suggestion);
            mapper.suggestsExchangeRateChange(suggestion);
        });
    }

    @Override
    public ExchangeRateSuggestion getSuggestion(String suggestionId) {
        ExchangeRateSuggestion exchangeRateSuggestion = mapper.getExchangeRateSuggestion(suggestionId);
        if (exchangeRateSuggestion != null)
            setRates(exchangeRateSuggestion);
        return exchangeRateSuggestion;
    }

    @Override
    public List<ExchangeRateSuggestion> getUserSuggestions(String userId) {
        List<ExchangeRateSuggestion> suggestions = mapper.getUserExchangeRateSuggestions(userId);
        suggestions.forEach(this::setRates);
        return suggestions;
    }

    private void setRates(ExchangeRateSuggestion exchangeRateSuggestion) {
        exchangeRateSuggestion.getSuggestedExchangeRate().setWatched(getSuggestionWatched());
        exchangeRateSuggestion.getSuggestedExchangeRate().setRates(mapper.getSuggestedRates(exchangeRateSuggestion.getSuggestedExchangeRate().getId()));
    }

    private Watched getSuggestionWatched(){
        return new Watched().setFrom(LocalDate.now());
    }
}
