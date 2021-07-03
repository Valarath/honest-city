package cz.honestcity.endpoints.configuration.login;

import cz.honestcity.model.vote.Vote;

/**
 * @author michal.keder
 */
public class VoteDeserializer extends DataDeserializer<Vote>{

    public VoteDeserializer() {
        super(Vote.class);
    }

    public VoteDeserializer(String packagePath) {
        super(Vote.class, packagePath);
    }
}
