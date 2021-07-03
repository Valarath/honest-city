package cz.honestcity.endpoints.subject;

import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.model.suggestion.Suggestion;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class GetSubjectsResponse {
    private Map<String, List<? extends WatchedSubject>> subjects;
    private Map<String, List<? extends Suggestion>> suggestions;
}
