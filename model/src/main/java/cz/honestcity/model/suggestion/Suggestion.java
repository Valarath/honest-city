package cz.honestcity.model.suggestion;

import cz.honestcity.model.exchange.Exchange;
import cz.honestcity.model.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.*;

@Data
@Accessors(chain = true)
public class Suggestion {
    private long suggestionId;
    private User user;
    private Exchange suggestedFor;
    private Image proofPicture;
    private Type type;
    private State state;
}
