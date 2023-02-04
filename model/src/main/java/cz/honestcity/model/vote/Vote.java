package cz.honestcity.model.vote;

import cz.honestcity.model.HonestCitySerializable;
import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Vote <SUGGESTION extends Suggestion> extends HonestCitySerializable {
    private Suggestion suggestion;
}
