package cz.honestcity.database.suggestion;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SuggestionPostgresMapper {

    void remove(long suggestionId);

    List<Suggestion> getUserSuggestions(long userId);

    Suggestion getSuggestion(long suggestionId);

    List<Suggestion> getExchangePointSuggestions(long exchangePointId);

    void reportNonExistingPoint(long exchangePointId, Suggestion suggestion);

    void suggestsNewExchangePoint(NewExchangePointSuggestion suggestion);

    void suggestsExchangeRateChange(ExchangeRateSuggestion suggestion);
}
