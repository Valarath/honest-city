package cz.honestcity.service.suggestion;

/**
 * @author michal.keder
 */

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.suggestion.Suggestion;

import java.util.List;

public interface NewSubjectSuggestionService<SUBJECT_SUGGESTION extends Suggestion> {

    List<? extends SUBJECT_SUGGESTION> getAllInArea(Position position);

}
