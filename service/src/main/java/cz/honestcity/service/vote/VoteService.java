package cz.honestcity.service.vote;

import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.subject.exchange.ExchangePointService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Map;

public abstract class VoteService<SUGGESTION extends Suggestion> {

    @Autowired
    protected VoteGateway voteGateway;

    protected ExchangePointService exchangePointService;

    protected VoteCalculationGateway voteCalculationGateway;

    protected Map<String, SuggestionService> suggestionServices;

    protected UserService userService;

    public abstract void upVote(SUGGESTION suggestion, String userId);

    protected void increaseSuggesterScore(User user) {
        userService.increaseUserScore(user);
    }

    protected void recordVote(SUGGESTION suggestion, String userId) {
        voteGateway.recordVote(suggestion.getId(), userId);
        getService(suggestion).increaseVotes(suggestion.getId());
    }

    protected boolean isSuggestionAcceptable(SUGGESTION suggestion, String userId) {
        Integer suggestionVotes = voteGateway.getNumberOfVotes(suggestion.getId());
        return suggestion.getState() == State.IN_PROGRESS
                && suggestionVotes != null
                && voteCalculationGateway.isAcceptable(userService.getUserScore(userId),suggestionVotes);
    }

    protected User getUser(Suggestion suggestion) {
        return suggestionServices.get(suggestion.getClassName())
                .getSuggestion(suggestion.getId())
                .getSuggestedBy();
    }

    protected SUGGESTION getSuggestion(SUGGESTION suggestion){
        return getService(suggestion).getSuggestion(suggestion.getId());
    }

    protected void updateSuggestion(SUGGESTION suggestion) {
        updateSuggestion(suggestion,suggestion.getSubjectId());
    }

    protected void updateSuggestion(SUGGESTION suggestion, String id) {
        suggestion.setSubjectId(id);
        suggestion.setState(State.ACCEPTED);
    }

    protected SuggestionService<SUGGESTION> getService(Suggestion suggestion){
        return suggestionServices.get(suggestion.getClassName());
    }

    @Autowired
    @Lazy
    public void setSuggestionServices(Map<String, SuggestionService> suggestionServices) {
        this.suggestionServices = suggestionServices;
    }

    @Autowired
    @Lazy
    public void setExchangeService(ExchangePointService exchangePointService) {
        this.exchangePointService = exchangePointService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setVoteCalculationGateway(VoteCalculationGateway voteCalculationGateway) {
        this.voteCalculationGateway = voteCalculationGateway;
    }
}
