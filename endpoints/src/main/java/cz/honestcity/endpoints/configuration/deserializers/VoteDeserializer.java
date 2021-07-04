package cz.honestcity.endpoints.configuration.deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.honestcity.model.vote.Vote;

/**
 * @author michal.keder
 */
public class VoteDeserializer extends DataDeserializer<Vote>{

    public VoteDeserializer(ObjectMapper objectMapper) {
        super(objectMapper, Vote.class);
    }

    public VoteDeserializer(ObjectMapper objectMapper, String packagePath) {
        super(objectMapper, Vote.class, packagePath);
    }

}
