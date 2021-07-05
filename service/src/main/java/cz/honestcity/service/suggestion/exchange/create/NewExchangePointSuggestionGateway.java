package cz.honestcity.service.suggestion.exchange.create;

import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.service.suggestion.SuggestionGateway;

import java.util.List;

public interface NewExchangePointSuggestionGateway extends SuggestionGateway<NewExchangePointSuggestion> {

    List<NewExchangePointSuggestion> getAll();

    List<NewExchangePointSuggestion> getExchangePointSuggestions(String exchangePointId);
}
