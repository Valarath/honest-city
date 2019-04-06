package cz.honestcity.endpoints.suggestion;

import cz.honestcity.endpoints.user.GetUserSuggestionsRequest;
import cz.honestcity.endpoints.user.GetUserSuggestionsResponse;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SuggestionController {

    @Autowired
    private Map<String, SuggestionService> suggestionServices;

    @PostMapping("/suggest")
    public void suggest(@RequestBody PostSuggestRequest request){
        suggestionServices.get(request.getSuggestionServiceType().name()).suggest(request.getNewExchangePointSuggestions());
    }

    @PostMapping("/remove")
    public void remove(@RequestBody RemoveSuggestionRequest request){
        suggestionServices.get(SuggestionServiceType.CLOSED_EXCHANGE_POINT).removeSuggestions(request.getSuggestions());
    }
}
