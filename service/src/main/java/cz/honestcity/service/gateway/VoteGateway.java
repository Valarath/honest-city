package cz.honestcity.service.gateway;

public interface VoteGateway {
    int getNumberOfVotes(long suggestionId,long userId);

    void recordVote(long suggestionId, long userId);
}
