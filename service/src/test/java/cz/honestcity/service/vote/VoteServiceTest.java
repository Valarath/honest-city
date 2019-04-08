/**package cz.honestcity.service.vote;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.exchange.ExchangeService;
import cz.honestcity.service.vote.VoteGateway;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.user.UserService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class VoteServiceTest {

	private static final int VOTES_ON_ACCEPTED_SCENARIO = 20;
	private static final long SUGGESTION_ID =1;
	private static final int USER_ID = 1;
	private static final int USER_SCORE = 0;

	@Mock
	private VoteGateway voteGateway;

	@Mock
	private SuggestionService suggestionService;

	@Mock
	private ExchangeService exchangeService;

	@Mock
	private UserService userService;

	@InjectMocks
	private VoteService voteService;


	@Test
	public void upVoteNewExchangePointSuggestion_suggestionAccepted() {
		prepareEnvironment_upVoteNewExchangePoint_suggestionAccepted();
		voteService.upVoteNewExchangePointSuggestion(SUGGESTION_ID, USER_ID);
	}

	private void prepareEnvironment_upVoteNewExchangePoint_suggestionAccepted() {
		NewExchangePointSuggestion suggestion = getNewExchangePointSuggestion();
		when(voteGateway.getNumberOfVotes(SUGGESTION_ID,USER_ID)).thenReturn(VOTES_ON_ACCEPTED_SCENARIO);
		when(userService.getUserScore(USER_ID)).thenReturn(USER_SCORE);
		when(suggestionService.getSuggestion(SUGGESTION_ID)).thenReturn(suggestion);
		doNothing().when(exchangeService).createExchange(getNewExchangePoint(suggestion));
		doNothing().when(userService).setUserScore(suggestion.getSuggestedBy());
	}

	private NewExchangePointSuggestion getNewExchangePointSuggestion() {
		return (NewExchangePointSuggestion) new NewExchangePointSuggestion()
				.setPosition(new Position())
				.setSuggestedBy(getUser());
	}

	private User getUser() {
		return new User()
				.setId(USER_ID)
				.setScore(USER_SCORE);
	}

	@Test
	public void upVoteNewExchangePointSuggestion_suggestionNotAccepted() {

		voteService.upVoteNewExchangePointSuggestion(SUGGESTION_ID,USER_ID);
	}

	@Test
	public void upVoteExchangePointRateChangeSuggestion() {
	}

	@Test
	public void upVoteDeleteExchangePointSuggestion() {
	}

	private ExchangePoint getNewExchangePoint(NewExchangePointSuggestion suggestion) {
		return (ExchangePoint) new ExchangePoint().setPosition(suggestion.getPosition());
	}
}**/