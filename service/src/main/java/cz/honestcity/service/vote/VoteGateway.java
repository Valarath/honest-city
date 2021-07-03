package cz.honestcity.service.vote;

public interface VoteGateway {
    Integer getNumberOfVotes(String suggestionId);

    void recordVote(String suggestionId, String userId);
}
