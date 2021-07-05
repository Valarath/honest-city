package cz.honestcity.database.vote;

import cz.honestcity.model.vote.Vote;
import cz.honestcity.service.vote.VoteGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotePostgresGateway implements VoteGateway {

    private final VotePostgresMapper votePostgresMapper;

    public VotePostgresGateway(VotePostgresMapper votePostgresMapper) {
        this.votePostgresMapper = votePostgresMapper;
    }

    @Override
    public Integer getNumberOfVotes(String suggestionId) {
        return votePostgresMapper.getNumberOfVotes(suggestionId);
    }

    @Override
    public boolean isVoteRecorded(String suggestionId, String userId) {
        return !votePostgresMapper.getVotes(suggestionId, userId).isEmpty();
    }

    @Override
    public void recordVote(String suggestionId, String userId) {
        votePostgresMapper.recordVote(suggestionId, userId);
    }
}
