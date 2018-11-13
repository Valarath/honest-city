package cz.honestcity.endpoints.suggestion;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RemoveSuggestionRequest {
    private long suggestionId;
}
