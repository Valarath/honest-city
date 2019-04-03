package cz.honestcity.database.exchange;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.suggestion.Type;
import cz.honestcity.service.gateway.ExchangeChangeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeChangePostgressGateway implements ExchangeChangeGateway {

    @Autowired
    private ExchangeChangeMapper exchangeChangeMapper;

    @Override
    public List<Suggestion> getSuggestions(Position exchangePosition, Type type, State state) {
        return exchangeChangeMapper.getSuggestions(exchangePosition);
    }

    @Override
    public void suggestsChange(Suggestion suggestion) {
        exchangeChangeMapper.suggestsChange(suggestion);
    }

    @Override
    public List<Suggestion> getSuggestionsInProgress(long userId) {
        return exchangeChangeMapper.getSuggestionsInProgress(userId);
    }
}
