package cz.honestcity.service.vote;

public interface VoteGateway {
    int getNumberOfVotes(String suggestionId);

    void recordVote(String suggestionId, String userId);
}
