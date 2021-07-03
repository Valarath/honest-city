package cz.honestcity.database.suggestion.exchange.create;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;

import java.awt.*;

public class NewExchangePointPostgresSuggestion extends NewExchangePointSuggestion {

    public NewExchangePointPostgresSuggestion(Double longitude, Double latitude, String suggestionId, String suggesterId, String suggesterUsername, String suggesterEmail, Integer suggesterScore, String state) {
        initializePosition(longitude, latitude);
        setId(suggestionId);
        setState(State.valueOf(state));
        initializeSuggester(suggesterId, suggesterUsername, suggesterScore, suggesterEmail);
    }

    private void initializeSuggester(String suggesterId, String suggesterUsername, Integer suggesterScore, String suggesterEmail) {
        setSuggestedBy(
                new User().setId(suggesterId)
                        .setScore(suggesterScore)
                        .setUsername(suggesterUsername)
                        .setEmail(suggesterEmail));
    }

    private void initializePosition(Double longitude, Double latitude) {
        setPosition(
                new Position()
                        .setLatitude(latitude)
                        .setLongitude(longitude));
    }
}
