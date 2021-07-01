package cz.honestcity.endpoints.subject;

import cz.honestcity.endpoints.BaseController;
import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.subject.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SubjectController extends BaseController {

    private final Map<String, SubjectService> subjectServices;

    public SubjectController(Map<String, SubjectService> subjectServices) {
        this.subjectServices = subjectServices;
    }

    @GetMapping(SubjectEndpointsUrl.SUBJECT_IN_AREA)
    public GetSubjectsResponse getSubjects(GetSubjectsRequest getSubjectsRequest) {
        Map<String, List<? extends WatchedSubject>> subjects =new HashMap<>();
        subjectServices.values().forEach(subjectService -> subjects.put(getWatchedSubjectClassName(subjectService), getSubjectsInArea(getSubjectsRequest, subjectService)));
        return new GetSubjectsResponse().setSubjects(subjects);
    }

    private List<? extends WatchedSubject> getSubjectsInArea(GetSubjectsRequest getSubjectsRequest, SubjectService subjectService) {
        return subjectService.getSubjectsInArea(getSubjectsRequest.getUserPosition());
    }

    private String getWatchedSubjectClassName(SubjectService subjectService) {
        return (subjectService.getClass().getDeclaredAnnotation(HonestCityService.class)).beanId().getSimpleName();
    }

}
