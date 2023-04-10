package cz.honestcity.endpoints.subject;

import cz.honestcity.service.subject.exchange.SubjectSettingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectSettingsController {

    private final SubjectSettingsService subjectSettingsService;

    public SubjectSettingsController(SubjectSettingsService subjectSettingsService) {
        this.subjectSettingsService = subjectSettingsService;
    }

    @GetMapping(SubjectEndpointsUrl.SUBJECT_SETTINGS)
    public GetSubjectSettingsResponse get(){
        return new GetSubjectSettingsResponse()
                .setSubjectSettings(subjectSettingsService.get());
    }
}
