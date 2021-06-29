package cz.honestcity.endpoints.subject;

import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.service.subject.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SubjectController {

    private final Map<String, SubjectService> subjectServices;

    public SubjectController(Map<String, SubjectService> subjectServices) {
        this.subjectServices = subjectServices;
    }

    @GetMapping(SubjectEndpointsUrl.SUBJECT_IN_AREA)
    public GetSubjectsResponse getSubjects(GetSubjectsRequest getSubjectsRequest) {
        Map<Class<? extends WatchedSubject>, List<? extends WatchedSubject>> subjects = new HashMap<>();
        subjectServices.values().forEach(subjectService -> subjects.put((Class<? extends WatchedSubject>) subjectService.getClass().arrayType(), subjectService.getSubjectsInArea(getSubjectsRequest.getUserPosition())));
        return new GetSubjectsResponse().setSubjects(subjects);
    }

}
