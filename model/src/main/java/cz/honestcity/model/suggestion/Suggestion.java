package cz.honestcity.model.suggestion;

import cz.honestcity.model.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Suggestion {
    private String id;
    private User suggestedBy;
    private State state;
    private int votes;
}
