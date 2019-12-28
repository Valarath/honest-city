package cz.honestcity.endpoints.suggestion;

import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.base.BaseSuggestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {

    private final Map<String, SuggestionService> suggestionServices;

    public SuggestionController(Map<String, SuggestionService> suggestionServices) {
        this.suggestionServices = suggestionServices;
    }

    @PostMapping("/suggest")
    public void suggest(@RequestBody PostSuggestRequest request) {
        if (!request.getNewExchangePointSuggestions().isEmpty())
            suggestionServices.get(request.getNewExchangePointSuggestions().get(0).getClass()).suggest(request.getNewExchangePointSuggestions());
    }

    @PostMapping("/remove")
    public void remove(@RequestBody RemoveSuggestionRequest request) {
        if (!request.getSuggestions().isEmpty())
            ((BaseSuggestionService) suggestionServices.get(request.getSuggestions().get(0).getClass())).removeSuggestions(request.getSuggestions());
    }
}
