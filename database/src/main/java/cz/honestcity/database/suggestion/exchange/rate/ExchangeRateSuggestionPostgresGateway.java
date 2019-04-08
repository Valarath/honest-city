package cz.honestcity.database.suggestion.exchange.rate;

import cz.honestcity.database.suggestion.SuggestionPostgresGateway;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.suggestion.exchange.rate.ExchangeRateSuggestionGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateSuggestionPostgresGateway extends SuggestionPostgresGateway implements ExchangeRateSuggestionGateway {

    @Autowired
    private ExchangeRateSuggestionPostgresMapper mapper;

    @Override
    public List<ExchangeRateSuggestion> getExchangePointSuggestions(long exchangePointId) {
        return mapper.getExchangePointSuggestions(exchangePointId);
    }

    @Override
    public void suggestsExchangeRateChange(List<ExchangeRateSuggestion> suggestions) {
        suggestions.forEach(suggestion ->{
            suggestion.setId(suggestionPostgresMapper.suggest(suggestion));
            mapper.suggestsExchangeRateChange(suggestion);
            voteServices.get(VoteType.VoteConstants.EXCHANGE_RATE_CHANGE).upVote(suggestion.getId(),suggestion.getSuggestedBy().getId());
        });
    }

    @Override
    public ExchangeRateSuggestion getExchangeRateSuggestion(long suggestionId) {
        ExchangeRatePostgresSuggestion exchangeRateSuggestion = mapper.getExchangeRateSuggestion(suggestionId);
        exchangeRateSuggestion.getSuggestedExchangeRate().setRates(mapper.getSuggestedRates(exchangeRateSuggestion.getSuggestedExchangeRate().getId()));
        return exchangeRateSuggestion;
    }

}
