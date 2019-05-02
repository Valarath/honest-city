package cz.honestcity.service.vote.exchange;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.base.AbstractServiceTest;
import cz.honestcity.service.subject.exchange.ExchangeService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import cz.honestcity.service.user.UserService;
import cz.honestcity.service.vote.VoteGateway;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Map;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public abstract class VoteExchangeServiceTest extends AbstractServiceTest {

    @Mock
    protected ExchangeService exchangeService;

    @Mock
    protected VoteGateway voteGateway;

    @Mock
    protected UserService userService;

    @Mock
    protected Map<String, SuggestionService> suggestionServices;


    protected void setCommonEnvironment(Suggestion suggestion,SuggestionServiceType type,SuggestionService service){
        setSuggestionService(suggestion,type,service);
        setCommonVoteGatewayEnvironment();
        setCommonUserServiceEnvironment(suggestion);
    }

    private void setCommonUserServiceEnvironment(Suggestion suggestion) {
        when(userService.getUserScore(USER_ID)).thenReturn(USER_SCORE);
        doNothing().when(userService).increaseUserScore(suggestion.getSuggestedBy());
    }

    private void setCommonVoteGatewayEnvironment() {
        when(voteGateway.getNumberOfVotes(SUGGESTION_ID)).thenReturn(VOTES_ON_ACCEPTED_SCENARIO);
        doNothing().when(voteGateway).recordVote(SUGGESTION_ID,USER_ID);
    }

    private void setSuggestionService(Suggestion suggestion,SuggestionServiceType type,SuggestionService service) {
        when(suggestionServices.get(type.name())).thenReturn(service);
        when(service.getSuggestion(SUGGESTION_ID)).thenReturn(suggestion);
    }


    protected void upVote_suggestionNotAccepted(VoteExchangeService service){
        setCommonVoteGatewayEnvironment();
        service.upVote(SUGGESTION_ID,USER_ID);
    }

}
