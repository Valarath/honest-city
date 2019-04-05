package cz.honestcity.database.suggestion;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SuggestionPostgresMapper {

    List<ExchangeRateSuggestion> getExchangePointSuggestions(long exchangePointId);

    void reportNonExistingPoint(List<NonExistingExchangePointSuggestion> nonExistingExchangePointSuggestions);

    void suggestsNewExchangePoint(List<NewExchangePointSuggestion> suggestions);

    void suggestsExchangeRateChange(List<ExchangeRateSuggestion> suggestions);

    void removeSuggestions(List<? extends Suggestion> toRemove);
}
