package cz.honestcity.endpoints.suggestion;

import cz.honestcity.service.exchange.ExchangeChangeService;
import cz.honestcity.service.suggestion.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {

    @Autowired
    private ExchangeChangeService exchangeChangeService;

    @Autowired
    private SuggestionService suggestionService;

    @GetMapping("/suggestions-for-exchange-point")
    public GetSuggestionsResponse getSuggestionsForExchangePoint(GetSuggestionsRequest request){
        return new GetSuggestionsResponse()
                .setSuggestions(exchangeChangeService.getSuggestions(
                        request.getPosition(),
                        request.getType(),
                        request.getState()));
    }

    @GetMapping("/user-suggestions")
    public GetUserSuggestionsResponse getUserSuggestions(GetUserSuggestionsRequest request){
        return new GetUserSuggestionsResponse()
                .setUserSuggestions(suggestionService.getUserSuggestions(request.getUserId()));
    }

    @PostMapping("/suggest-exhange-rate-change")
    public void suggestUpdateOfExchangePoint(@RequestBody PostExchangeRateChangeRequest request){
        suggestionService.suggestsExchangeRateChange(request.getSuggestion(),request.getExchangePointId(),request.getSuggestedExchangeRate());
    }

    @PostMapping("/suggest-exchange-point")
    public void suggestNewExchangePoint(@RequestBody PostNewExchangePointRequest request){
        suggestionService.suggestsNewExchangePoint(request.getSuggestion(),request.getPosition());
    }

    @DeleteMapping("/remove")
    public void remove(RemoveSuggestionRequest request){
        suggestionService.remove(request.getSuggestionId());

    }

    @DeleteMapping("/report-non-existing-endpoint")
    public void reportNonExistingPoint(ReportNonExistingExchangePointRequest request){
        suggestionService.reportNonExistingPoint(request.getExchangePointId(),request.getSuggestion());

    }
}
