package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.login.LoginDataService;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import cz.honestcity.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@HonestCityService(beanId = Suggestion.class)
public abstract class SuggestionService<SUGGESTION extends Suggestion> {

    protected LoginDataService loginDataService;

    protected final BaseSuggestionGateway suggestionGateway;

    public SuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway) {
        this.suggestionGateway = suggestionGateway;
    }
    public abstract List<SUGGESTION> getScoredSuggestions(String exchangePointId);

    public abstract void suggest(List<SUGGESTION> suggestions);

    public abstract SUGGESTION getSuggestion(String suggestionId);

    public abstract List<SUGGESTION> getUserSuggestions(String userId);

    protected int compareUserScore(Suggestion userSuggestion1, Suggestion userSuggestion2) {
        return Integer.compare(userSuggestion1.getSuggestedBy().getScore(), userSuggestion2.getSuggestedBy().getScore());
    }

    protected void suggesterVotesForHisSuggestions(List<? extends Suggestion> suggestions, VoteService service) {
        suggestions.forEach(suggestion ->
                service.upVote(suggestion, suggestion.getSuggestedBy().getId())
        );
    }

    protected List<SUGGESTION> getSuggestibleSuggestions(List<SUGGESTION> suggestions, SuggestionGateway<SUGGESTION> gateway) {
        return suggestions.stream()
                .filter(it -> gateway.getSuggestion(it.getId()) == null)
                .collect(Collectors.toList());
    }

    protected SUGGESTION setSuggestorLoginData(SUGGESTION suggestion){
        suggestion.getSuggestedBy().setLoginData(loginDataService.get(suggestion.getSuggestedBy().getId()));
        return suggestion;
    }

    public void increaseVotes(String suggestionId){
        Suggestion suggestion = suggestionGateway.getSuggestion(suggestionId);
        suggestion.setVotes(suggestion.getVotes()+1);
        suggestionGateway.update(suggestion);
    }

    public void removeSuggestions(List<SUGGESTION> toRemove){
        suggestionGateway.removeSuggestions(toRemove);
    }

    @Autowired
    public void setLoginDataService(LoginDataService loginDataService) {
        this.loginDataService = loginDataService;
    }

    public void update(SUGGESTION suggestion) {
        suggestionGateway.update(suggestion);
    }
}
