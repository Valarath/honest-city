package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;

@Data
public class PostNewExchangePointRequest {
    private Suggestion suggestion;
    private Position position;
}
