package cz.honestcity.service.vote;

public interface VoteGateway {

    Integer getNumberOfVotes(String suggestionId);

    boolean isVoteRecorded(String suggestionId, String userId);

    void recordVote(String suggestionId, String userId);
}
