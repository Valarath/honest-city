package cz.honestcity.model.suggestion;

import cz.honestcity.model.HonestCitySerializable;
import cz.honestcity.model.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class Suggestion extends HonestCitySerializable {
    private String id;
    private User suggestedBy;
    private String subjectId;
    private State state;
    private int votes;
    private Instant createdAt;
}
