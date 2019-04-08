package cz.honestcity.database.suggestion;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.gateway.SuggestionGateway;
import cz.honestcity.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SuggestionPostgresGateway implements SuggestionGateway {

    @Autowired
    private SuggestionPostgresMapper suggestionPostgresMapper;

    @Autowired
    private Map<String, VoteService> voteServices;

    @Override
    public List<ExchangeRateSuggestion> getExchangePointSuggestions(long exchangePointId) {
        return suggestionPostgresMapper.getExchangePointSuggestions(exchangePointId);
    }

    @Override
    public void reportNonExistingPoint(List<NonExistingExchangePointSuggestion> nonExistingExchangePointSuggestions) {
        nonExistingExchangePointSuggestions.forEach(suggestion ->{
            suggestion.setId(suggestionPostgresMapper.suggest(suggestion));
            suggestionPostgresMapper.reportNonExistingPoint(suggestion);
            voteServices.get(VoteType.VoteConstants.DELETE_EXCHANGE_POINT).upVote(suggestion.getId(),suggestion.getSuggestedBy().getId());
        });
    }

    @Override
    public void suggestsNewExchangePoint(List<NewExchangePointSuggestion> suggestions) {
        suggestions.forEach(suggestion ->{
            suggestion.setId(suggestionPostgresMapper.suggest(suggestion));
            suggestionPostgresMapper.suggestsNewExchangePoint(suggestion);
            voteServices.get(VoteType.VoteConstants.NEW_EXCHANGE_POINT).upVote(suggestion.getId(),suggestion.getSuggestedBy().getId());
        });
    }

    @Override
    public void suggestsExchangeRateChange(List<ExchangeRateSuggestion> suggestions) {
        suggestions.forEach(suggestion ->{
            suggestion.setId(suggestionPostgresMapper.suggest(suggestion));
            suggestionPostgresMapper.suggestsExchangeRateChange(suggestion);
            voteServices.get(VoteType.VoteConstants.EXCHANGE_RATE_CHANGE).upVote(suggestion.getId(),suggestion.getSuggestedBy().getId());
        });
    }

    @Override
    public void removeSuggestions(List<? extends Suggestion> toRemove) {
        suggestionPostgresMapper.removeSuggestions(toRemove);
    }

    @Override
    public ExchangeRateSuggestion getExchangeRateSuggestion(long suggestionId) {
        ExchangeRatePostgresSuggestion exchangeRateSuggestion = suggestionPostgresMapper.getExchangeRateSuggestion(suggestionId);
        return exchangeRateSuggestion.getSuggestedExchangeRate().setRates();
    }

    @Override
    public NonExistingExchangePointSuggestion getNonExistingExchangePointSuggestion(long suggestionId) {
        return suggestionPostgresMapper.getNonExistingExchangePointSuggestion(suggestionId);
    }

    @Override
    public NewExchangePointSuggestion getNewExchangePointSuggestion(long suggestionId) {
        return suggestionPostgresMapper.getNewExchangePointSuggestion(suggestionId);
    }

}
