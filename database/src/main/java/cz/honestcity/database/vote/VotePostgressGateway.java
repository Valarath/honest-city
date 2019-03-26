package cz.honestcity.database.vote;

import cz.honestcity.service.gateway.VoteGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotePostgressGateway implements VoteGateway {

    @Autowired
    private VotePostgressMapper votePostgressMapper;

    @Override
    public int getNumberOfVotes(long suggestionId, long userId) {
        return votePostgressMapper.getNumberOfVotes(suggestionId, userId);
    }

    @Override
    public void recordVote(long suggestionId, long userId) {
        votePostgressMapper.recordVote(suggestionId,userId);
    }
}
