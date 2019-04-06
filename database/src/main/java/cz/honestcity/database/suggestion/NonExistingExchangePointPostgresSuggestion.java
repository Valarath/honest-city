package cz.honestcity.database.suggestion;

import cz.honestcity.model.suggestion.NonExistingExchangePointSuggestion;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.user.User;

public class NonExistingExchangePointPostgresSuggestion extends NonExistingExchangePointSuggestion {

    public NonExistingExchangePointPostgresSuggestion(Long exchangePointId, Long suggestionId, Long suggesterId, String suggesterUsername, Integer suggesterScore, State state ) {
        setExchangePointId(exchangePointId);
        setId(suggestionId);
        setState(state);
        initializeSuggester(suggesterId, suggesterUsername, suggesterScore);
    }

    private void initializeSuggester(Long suggesterId, String suggesterUsername, Integer suggesterScore) {
        setSuggestedBy(
                new User().setId(suggesterId)
                        .setScore(suggesterScore)
                        .setUsername(suggesterUsername));
    }
}
