package cz.honestcity.endpoints.suggestion;

import cz.honestcity.endpoints.BaseController;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.suggestion.SuggestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SuggestionController extends BaseController {

    private final Map<String, SuggestionService> suggestionServices;

    public SuggestionController(Map<String, SuggestionService> suggestionServices) {
        this.suggestionServices = suggestionServices;
    }

    @PostMapping(SuggestionEndpointsUrl.SUGGEST)
    public void suggest(@RequestBody PostSuggestRequest request) {
        getUser();
        if (!request.getNewExchangePointSuggestions().isEmpty())
            suggest(request.getNewExchangePointSuggestions());
    }

    private void suggest( List<? extends Suggestion> suggestions){
        setSuggestor(suggestions);
        var suggestionsByClass = getDtosByClass(suggestions);
        suggestionsByClass.forEach((key, value) -> suggestionServices.get(key).suggest(value));
    }

    private void setSuggestor(List<? extends Suggestion> suggestions) {
        User loggedUser = getUser();
        suggestions.forEach(
                it -> it.setSuggestedBy(loggedUser)
        );
    }

    @PostMapping(SuggestionEndpointsUrl.REMOVE)
    public void remove(@RequestBody RemoveSuggestionRequest request) {
        if (!request.getSuggestions().isEmpty())
            remove(request.getSuggestions());
    }

    private void remove( List<? extends Suggestion> suggestions){
        var suggestionsByClass = getDtosByClass(suggestions);
        suggestionsByClass.forEach((key, value) -> suggestionServices.get(key).removeSuggestions(value));
    }

}
