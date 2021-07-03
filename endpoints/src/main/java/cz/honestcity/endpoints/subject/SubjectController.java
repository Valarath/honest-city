package cz.honestcity.endpoints.subject;

import cz.honestcity.endpoints.BaseController;
import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.subject.SubjectService;
import cz.honestcity.service.suggestion.NewSubjectSuggestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SubjectController extends BaseController {

    private final Map<String, SubjectService> subjectServices;
    private final List<NewSubjectSuggestionService<? extends Suggestion>> newSubjectSuggestionServices;

    public SubjectController(Map<String, SubjectService> subjectServices, List<NewSubjectSuggestionService<? extends Suggestion>> newSubjectSuggestionServices) {
        this.subjectServices = subjectServices;
        this.newSubjectSuggestionServices = newSubjectSuggestionServices;
    }

    @GetMapping(SubjectEndpointsUrl.SUBJECT_IN_AREA)
    public GetSubjectsResponse getSubjects(GetSubjectsRequest getSubjectsRequest) {
        return new GetSubjectsResponse()
                .setSubjects(getSubjectsByClassName(getSubjectsRequest))
                .setNewSubjectSuggestions(getSuggestionsByClassName(getSubjectsRequest));
    }

    private Map<String, List<? extends WatchedSubject>> getSubjectsByClassName(GetSubjectsRequest getSubjectsRequest) {
        Map<String, List<? extends WatchedSubject>> subjects = new HashMap<>();
        subjectServices.values().forEach(subjectService -> subjects.put(getWatchedSubjectClassName(subjectService), getSubjectsInArea(getSubjectsRequest, subjectService)));
        return subjects;
    }

    private Map<String, List<? extends Suggestion>> getSuggestionsByClassName(GetSubjectsRequest getSubjectsRequest) {
        return newSubjectSuggestionServices.stream()
                .map(it -> it.getAllInArea(getSubjectsRequest.getUserPosition()))
                .collect(Collectors.toMap(this::getSuggestionClassName, it -> it));
    }

    private String getSuggestionClassName(List<? extends Suggestion> suggestions) {
        return suggestions.isEmpty()? "":suggestions.stream()
                .findFirst()
                .orElseThrow()
                .getClassName();
    }

    private List<? extends WatchedSubject> getSubjectsInArea(GetSubjectsRequest getSubjectsRequest, SubjectService subjectService) {
        return subjectService.getSubjectsInArea(getSubjectsRequest.getUserPosition());
    }

    private String getWatchedSubjectClassName(SubjectService subjectService) {
        return (subjectService.getClass().getDeclaredAnnotation(HonestCityService.class)).beanId().getSimpleName();
    }

}
