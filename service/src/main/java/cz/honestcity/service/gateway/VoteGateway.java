package cz.honestcity.service.gateway;

public interface VoteGateway {
    int getNumberOfVotes(long suggestionId);

    void recordVote(long suggestionId, long userId);
}
