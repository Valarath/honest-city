package cz.honestcity.service.suggestion.base;

import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.SuggestionService;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

//@Service(SuggestionServiceType.SuggestionServiceTypeNames.BASE_SERVICE)
@HonestCityService(beanId = Suggestion.class)
public class BaseSuggestionService extends SuggestionService {

    public BaseSuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway) {
        super(suggestionGateway);
    }

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
