package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.State;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.suggestion.Type;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SuggestionRequest {
    private Position position;
    private Type type;
    private State state;
}
