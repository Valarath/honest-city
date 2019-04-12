package cz.honestcity.endpoints.subject;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.service.subject.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private Map<String, SubjectService> subjectServices;

    @GetMapping("/subjects-in-area")
    public GetSubjectsResponse getSubjects(GetSubjectsRequest getSubjectsRequest) {
        Map<Class<? extends WatchedSubject>, List<? extends WatchedSubject>> subjects = new HashMap<>();
        subjectServices.values().forEach(subjectService -> subjects.put((Class<? extends WatchedSubject>) subjectService.getClass().arrayType(), subjectService.getSubjectsInArea(getSubjectsRequest.getUserPosition())));
        return new GetSubjectsResponse().setSubjects(subjects);
    }

}
