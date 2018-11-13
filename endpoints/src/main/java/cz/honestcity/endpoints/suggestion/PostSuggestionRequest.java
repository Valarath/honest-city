package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostSuggestionRequest {
    private Suggestion suggestion;
}
