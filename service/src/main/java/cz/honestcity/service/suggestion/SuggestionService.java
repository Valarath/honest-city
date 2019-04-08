package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class SuggestionService {

    @Autowired
    protected SuggestionGateway suggestionGateway;

    public abstract void suggest(List<? extends Suggestion> suggestions);

    public abstract Suggestion getSuggestion(long suggestionId);


}
