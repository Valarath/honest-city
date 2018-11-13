package cz.honestcity.endpoints.suggestion;

import cz.honestcity.service.exchange.ExchangeChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {

    @Autowired
    private ExchangeChangeService exchangeChangeService;

    @GetMapping("/suggestions")
    public SuggestionResponse getSuggestions(SuggestionRequest request){
        return new SuggestionResponse()
                .setSuggestions(exchangeChangeService.getSuggestions(
                        request.getPosition(),
                        request.getType(),
                        request.getState()));
    }
    @PostMapping("/suggest")
    public void suggest(@RequestBody PostSuggestionRequest request){
        exchangeChangeService.suggestsChange(request.getSuggestion());
    }

    @DeleteMapping("/remove")
    public void remove(RemoveSuggestionRequest request){
        exchangeChangeService.remove(request.getSuggestionId(),5);

    }
}
