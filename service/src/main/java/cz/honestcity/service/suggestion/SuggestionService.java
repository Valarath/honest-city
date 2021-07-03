package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class SuggestionService {

    protected final BaseSuggestionGateway suggestionGateway;

    public SuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway) {
        this.suggestionGateway = suggestionGateway;
    }

    public abstract void suggest(List<? extends Suggestion> suggestions);

    public abstract Suggestion getSuggestion(String suggestionId);

    public abstract List<? extends Suggestion> getUserSuggestions(String userId);

    public abstract void removeSuggestions(List<? extends Suggestion> toRemove);
}
