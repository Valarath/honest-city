package cz.honestcity.model.suggestion;


import cz.honestcity.model.subject.Position;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NewExchangePointSuggestion extends Suggestion{
    private Position position;
}
