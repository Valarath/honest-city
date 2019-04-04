package cz.honestcity.endpoints.suggestion;

import cz.honestcity.model.subject.Position;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetSuggestionsRequest {
    private Position position;
}
