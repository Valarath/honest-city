package cz.honestcity.endpoints.suggestion;

import cz.honestcity.endpoints.user.GetUserSuggestionsRequest;
import cz.honestcity.endpoints.user.GetUserSuggestionsResponse;
import cz.honestcity.service.suggestion.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @PostMapping("/suggest-exhange-rate-change")
    public void suggestUpdateOfExchangePoint(@RequestBody PostExchangeRateChangeRequest request){
        suggestionService.suggestsExchangeRateChange(request.getExchangeRateSuggestions());
    }

    @PostMapping("/suggest-exchange-point")
    public void suggestNewExchangePoint(@RequestBody PostNewExchangePointRequest request){
        suggestionService.suggestsNewExchangePoint(request.getNewExchangePointSuggestions());
    }

    @DeleteMapping("/remove")
    public void remove(RemoveSuggestionRequest request){
        suggestionService.removeSuggestions(request.getSuggestions());

    }

    @DeleteMapping("/report-non-existing-endpoint")
    public void reportNonExistingPoint(ReportNonExistingExchangePointRequest request){
        suggestionService.reportNonExistingPoint(request.getNonExistingExchangePointSuggestions());

    }
}
