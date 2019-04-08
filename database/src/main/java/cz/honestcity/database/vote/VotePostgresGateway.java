package cz.honestcity.database.vote;

import cz.honestcity.service.vote.VoteGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotePostgresGateway implements VoteGateway {

    @Autowired
    private VotePostgresMapper votePostgresMapper;

    @Override
    public int getNumberOfVotes(long suggestionId) {
        return votePostgresMapper.getNumberOfVotes(suggestionId);
    }

    @Override
    public void recordVote(long suggestionId, long userId) {
        votePostgresMapper.recordVote(suggestionId,userId);
    }
}
