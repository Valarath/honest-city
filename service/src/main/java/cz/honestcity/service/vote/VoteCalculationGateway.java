package cz.honestcity.service.vote;

public interface VoteCalculationGateway {

	boolean isAcceptable(int userScore, int suggestionVotes);

}
