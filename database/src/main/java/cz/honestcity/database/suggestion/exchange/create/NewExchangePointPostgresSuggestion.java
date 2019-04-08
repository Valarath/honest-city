package cz.honestcity.database.suggestion.exchange.create;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;

import java.awt.*;

public class NewExchangePointPostgresSuggestion extends NewExchangePointSuggestion {

    public NewExchangePointPostgresSuggestion(Double longitude, Double latitude, Long suggestionId, Long suggesterId, String suggesterUsername, Integer suggesterScore, State state ) {
        initializePosition(longitude, latitude);
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

    private void initializePosition(Double longitude, Double latitude) {
        setPosition(
                new Position()
                .setLatitude(latitude)
                .setLongitude(longitude));
    }
}
