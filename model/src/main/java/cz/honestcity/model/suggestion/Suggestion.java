package cz.honestcity.model.suggestion;

import cz.honestcity.model.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.*;

@Data
@Accessors(chain = true)
public class Suggestion {
    private long id;
    private User suggestedBy;
    private State state;
}
