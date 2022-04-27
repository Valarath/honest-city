package cz.honestcity.service.suggestion.base;

import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.suggestion.SuggestionGateway;

import java.util.List;

public interface BaseSuggestionGateway extends SuggestionGateway<Suggestion> {

    void removeSuggestions(List<? extends Suggestion> toRemove);

    void update(Suggestion suggestion);
}
