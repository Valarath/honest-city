package cz.honestcity.service.gateway;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.suggestion.Type;

import java.util.List;

public interface ExchangeChangeGateway {
    List<Suggestion> getSuggestions(Position exchangePosition, Type type, State state);

    void suggestsChange(Suggestion suggestion);
    
    List<Suggestion> getSuggestionsInProgress(long userId);

}
