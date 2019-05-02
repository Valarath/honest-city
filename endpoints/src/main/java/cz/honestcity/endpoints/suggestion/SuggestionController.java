package cz.honestcity.endpoints.suggestion;

import cz.honestcity.service.suggestion.base.BaseSuggestionService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {

    @Autowired
    private Map<String, SuggestionService> suggestionServices;

    @PostMapping("/suggest")
    public void suggest(@RequestBody PostSuggestRequest request){
        suggestionServices.get(request.getSuggestionServiceType().name()).suggest(request.getNewExchangePointSuggestions());
    }

    @PostMapping("/remove")
    public void remove(@RequestBody RemoveSuggestionRequest request){
        ((BaseSuggestionService)suggestionServices.get(SuggestionServiceType.BASE_SERVICE)).removeSuggestions(request.getSuggestions());
    }
}
