package cz.honestcity.service.suggestion.base;

import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(SuggestionServiceType.SuggestionServiceTypeNames.BASE_SERVICE)
public class BaseSuggestionService extends SuggestionService {

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {

    }
    @Override
    public Suggestion getSuggestion(long suggestionId) {
        return null;
    }

    @Override
    public List<? extends Suggestion> getUserSuggestions(long userId) {
        return null;
    }

    public void removeSuggestions(List<? extends Suggestion> toRemove){
        suggestionGateway.removeSuggestions(toRemove);
    }
}
