package cz.honestcity.model.vote;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class Vote {
    //private VoteType voteType;
    private long suggestionId;
}
