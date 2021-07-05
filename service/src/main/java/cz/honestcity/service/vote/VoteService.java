package cz.honestcity.service.vote;

import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.model.vote.Vote;
import cz.honestcity.service.subject.exchange.ExchangeService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Map;

public abstract class VoteService<VOTE extends Vote,SUGGESTION extends Suggestion> {

    //TODO to config file
    private static final int LOWEST_VALUE_FOR_ACCEPTENCE = 5;

    @Autowired
    private VoteGateway voteGateway;

    protected ExchangeService exchangeService;

    protected Map<String, SuggestionService> suggestionServices;

    protected UserService userService;

    public abstract void upVote(SUGGESTION suggestion, String userId);

    protected void increaseSuggesterScore(User user) {
        userService.increaseUserScore(user);
    }

    protected void recordVote(String suggestionId, String userId) {
        voteGateway.recordVote(suggestionId, userId);
    }

    protected boolean isSuggestionAcceptable(String suggestionId, String userId) {
        Integer suggestionVotes = voteGateway.getNumberOfVotes(suggestionId);
        return suggestionVotes != null && suggestionVotes * calculateUserTrustworthiness(userId) > LOWEST_VALUE_FOR_ACCEPTENCE;
    }

    private double calculateUserTrustworthiness(String userId) {
        return Math.atan(userService.getUserScore(userId) + Double.MIN_VALUE);
    }

    protected User getUser(Suggestion suggestion) {
        return suggestionServices.get(suggestion.getClassName())
                .getSuggestion(suggestion.getId())
                .getSuggestedBy();
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
    public void setExchangeService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
