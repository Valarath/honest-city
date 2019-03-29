package cz.honestcity.service.vote;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.suggestion.DeleteExchangePointSuggestion;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.exchange.ExchangeService;
import cz.honestcity.service.gateway.VoteGateway;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private static final int LOWEST_VALUE_FOR_ACCEPTENCE = 5;

    @Autowired
    private VoteGateway voteGateway;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private UserService userService;

    public void upVoteNewExchangePointSuggestion(long suggestionId, long userId) {
        if(isSuggestionAcceptable(suggestionId, userId))
            acceptNewExchangePoint(suggestionId);
        recordVote(suggestionId,userId);
    }

    public void upVoteExchangePointRateChangeSuggestion(long suggestionId, long userId) {
        if(isSuggestionAcceptable(suggestionId, userId))
            acceptExchangeRateChange(suggestionId);
        recordVote(suggestionId,userId);
    }

    public void upVoteDeleteExchangePointSuggestion(long suggestionId, long userId) {
        if(isSuggestionAcceptable(suggestionId, userId))
            acceptDeleteExchangePoint(suggestionId);
        recordVote(suggestionId,userId);
    }

    private void acceptExchangeRateChange(long suggestionId){
        ExchangeRateSuggestion suggestion = (ExchangeRateSuggestion) suggestionService.getSuggestion(suggestionId);
        exchangeService.changeExchangeRate(suggestion.getSuggestedExchangeRate().getId(),suggestion.getExchangePointId());
        userService.increaseUserScore(suggestion.getSuggestedBy());
    }

    private void acceptNewExchangePoint(long suggestionId){
        NewExchangePointSuggestion suggestion = (NewExchangePointSuggestion) suggestionService.getSuggestion(suggestionId);
        exchangeService.createExchange(getNewExchangePoint(suggestion));
        userService.increaseUserScore(suggestion.getSuggestedBy());
    }

    private void acceptDeleteExchangePoint(long suggestionId){
        DeleteExchangePointSuggestion suggestion = (DeleteExchangePointSuggestion) suggestionService.getSuggestion(suggestionId);
        exchangeService.deleteExchangePoint(suggestion.getExchangePointId());
        userService.increaseUserScore(suggestion.getSuggestedBy());
    }

    private ExchangePoint getNewExchangePoint(NewExchangePointSuggestion suggestion) {
        return (ExchangePoint) new ExchangePoint().setPosition(suggestion.getPosition());
    }

    private void recordVote(long suggestionId, long userId){
        voteGateway.recordVote(suggestionId,userId);
    }

    private boolean isSuggestionAcceptable(long suggestionId, long userId) {
        return voteGateway.getNumberOfVotes(suggestionId,userId)*calculateUserTrustworthiness(userId)> LOWEST_VALUE_FOR_ACCEPTENCE;
    }

    private double calculateUserTrustworthiness(long userId){
        return Math.atan(userService.getUserScore(userId));
    }
}
