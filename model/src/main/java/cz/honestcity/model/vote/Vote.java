package cz.honestcity.model.vote;

import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class Vote {
    //private VoteType voteType;
    private Suggestion suggestion;
}
