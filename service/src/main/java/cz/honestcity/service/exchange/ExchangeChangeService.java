package cz.honestcity.service.exchange;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.suggestion.Type;
import cz.honestcity.service.gateway.ExchangeChangeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeChangeService {

    @Autowired
    private ExchangeChangeGateway exchangeChangeGateway;

    public List<Suggestion> getSuggestions(Position exchangePosition, Type type, State state){
        return exchangeChangeGateway.getSuggestions(exchangePosition,type,state).stream()
                .sorted(this::compareUserScore)
                .collect(Collectors.toList());
    }

    private int compareUserScore(Suggestion userSuggestion1,Suggestion userSuggestion2){
        return Integer.compare(userSuggestion1.getUser().getScore(),userSuggestion2.getUser().getScore());
    }

    public void suggestsChange(Suggestion suggestion) {
        exchangeChangeGateway.suggestsChange(suggestion);
    }

    public List<Suggestion> getUserSuggestionsInProgress(long userId){
        return exchangeChangeGateway.getSuggestionsInProgress(userId);
    }
}
