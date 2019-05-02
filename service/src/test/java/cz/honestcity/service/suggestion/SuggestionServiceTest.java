package cz.honestcity.service.suggestion;

import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.service.base.AbstractServiceTest;

import java.util.ArrayList;
import java.util.List;

public abstract class SuggestionServiceTest extends AbstractServiceTest {

    protected  <T> List<? extends T> getSuggestionsForTest(Class<T> clazz) {
        return new ArrayList<T>();
    }

}
