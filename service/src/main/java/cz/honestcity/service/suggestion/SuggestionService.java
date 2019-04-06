package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.*;
import cz.honestcity.service.gateway.SuggestionGateway;
import cz.honestcity.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public abstract class SuggestionService {

    @Autowired
    protected SuggestionGateway suggestionGateway;

    public abstract void suggest(List<? extends Suggestion> suggestions);

    public void removeSuggestions(List<? extends Suggestion> toRemove){
        suggestionGateway.removeSuggestions(toRemove);
    }


}
