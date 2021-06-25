package cz.honestcity.database.suggestion.exchange.closed;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.user.User;

public class ClosedExchangePointPostgresSuggestion extends ClosedExchangePointSuggestion {

    public ClosedExchangePointPostgresSuggestion(String exchangePointId, String suggestionId, String suggesterId, String suggesterUsername, Integer suggesterScore, State state ) {
        setExchangePointId(exchangePointId);
        setId(suggestionId);
        setState(state);
        initializeSuggester(suggesterId, suggesterUsername, suggesterScore);
    }

    private void initializeSuggester(String suggesterId, String suggesterUsername, Integer suggesterScore) {
        setSuggestedBy(
                new User().setId(suggesterId)
                        .setScore(suggesterScore)
                        .setUsername(suggesterUsername));
    }
}
