package cz.honestcity.service.suggestion.base;

import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@HonestCityService(beanId = Suggestion.class)
public class BaseSuggestionService extends SuggestionService {

    public BaseSuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway) {
        super(suggestionGateway);
    }

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
    }

    @Override
    public Suggestion getSuggestion(String suggestionId) {
        return null;
    }

    @Override
    public List<? extends Suggestion> getUserSuggestions(String userId) {
        return null;
    }

    public void removeSuggestions(List<? extends Suggestion> toRemove){
        suggestionGateway.removeSuggestions(toRemove);
    }

    protected void suggesterVotesForHisSuggestions(List<? extends Suggestion> suggestions, VoteService service) {
        suggestions.forEach(suggestion ->
                service.upVote(suggestion, suggestion.getSuggestedBy().getId())
        );
    }

}
