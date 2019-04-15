package cz.honestcity.service.vote.exchange;

import cz.honestcity.service.base.AbstractServiceTest;
import cz.honestcity.service.subject.exchange.ExchangeService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.user.UserService;
import cz.honestcity.service.vote.VoteGateway;
import org.junit.Before;
import org.mockito.Mock;

import java.util.Map;

public abstract class VoteExchangeServiceTest extends AbstractServiceTest {

    @Mock
    protected ExchangeService exchangeService;

    @Mock
    protected VoteGateway voteGateway;

    @Mock
    protected UserService userService;

    @Mock
    protected Map<String, SuggestionService> suggestionServices;

    protected static final int VOTES_ON_ACCEPTED_SCENARIO = 20;
    protected static final long SUGGESTION_ID =1;
    protected static final long USER_ID = 1;
    protected static final int USER_SCORE = 0;
    protected static final long EXCHANGE_POINT_ID = 1;

}
