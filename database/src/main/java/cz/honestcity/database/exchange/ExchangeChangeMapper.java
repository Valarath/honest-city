package cz.honestcity.database.exchange;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.Suggestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExchangeChangeMapper {
    List<Suggestion> getSuggestionsInProgress(long userId);

    void suggestsChange(Suggestion suggestion);

    List<Suggestion> getSuggestions(Position exchangePosition);
}
