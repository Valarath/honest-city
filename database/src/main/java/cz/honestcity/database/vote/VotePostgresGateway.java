package cz.honestcity.database.vote;

import cz.honestcity.service.vote.VoteGateway;
import org.springframework.stereotype.Service;

@Service
public class VotePostgresGateway implements VoteGateway {

    private final VotePostgresMapper votePostgresMapper;

    public VotePostgresGateway(VotePostgresMapper votePostgresMapper) {
        this.votePostgresMapper = votePostgresMapper;
    }

    @Override
    public int getNumberOfVotes(String suggestionId) {
        return votePostgresMapper.getNumberOfVotes(suggestionId);
    }

    @Override
    public void recordVote(String suggestionId, String userId) {
        votePostgresMapper.recordVote(suggestionId, userId);
    }
}
