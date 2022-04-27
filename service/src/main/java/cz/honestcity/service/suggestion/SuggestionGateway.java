package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.Suggestion;

import java.util.List;

public interface SuggestionGateway<SUGGESTION extends Suggestion> {

    void suggests(List<SUGGESTION> suggestions);

    SUGGESTION getSuggestion(String suggestionId);

    List<SUGGESTION> getUserSuggestions(String userId);

}
