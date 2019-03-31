package cz.honestcity.database.exchange;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.suggestion.Type;
import cz.honestcity.service.gateway.ExchangeChangeGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeChangePostgressGateway implements ExchangeChangeGateway {
    @Override
    public List<Suggestion> getSuggestions(Position exchangePosition, Type type, State state) {
        return null;
    }

    @Override
    public void suggestsChange(Suggestion suggestion) {

    }

    @Override
    public List<Suggestion> getSuggestionsInProgress(long userId) {
        return null;
    }
}
