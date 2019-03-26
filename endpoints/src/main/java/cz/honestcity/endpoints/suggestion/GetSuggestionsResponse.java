package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class GetSuggestionsResponse {
    private List<Suggestion> suggestions;

}
