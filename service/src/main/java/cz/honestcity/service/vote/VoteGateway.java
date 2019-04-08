package cz.honestcity.service.vote;

public interface VoteGateway {
    int getNumberOfVotes(long suggestionId);

    void recordVote(long suggestionId, long userId);
}
