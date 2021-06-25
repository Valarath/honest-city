package cz.honestcity.database.suggestion.exchange.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.user.User;

public class ExchangeRatePostgresSuggestion extends ExchangeRateSuggestion {

    public ExchangeRatePostgresSuggestion(String exchangePointId, String suggestionId, String suggesterId, String suggesterUsername, Integer suggesterScore, Integer votes, State state, String exchangeRateId ) {
        setExchangePointId(exchangePointId);
        setId(suggestionId);
        setState(state);
        setVotes(votes);
        setSuggestedExchangeRate(new ExchangeRate().setId(exchangeRateId));
        initializeSuggester(suggesterId, suggesterUsername, suggesterScore);
    }

    private void initializeSuggester(String suggesterId, String suggesterUsername, Integer suggesterScore) {
        setSuggestedBy(
                new User().setId(suggesterId)
                        .setScore(suggesterScore)
                        .setUsername(suggesterUsername));
    }

}
