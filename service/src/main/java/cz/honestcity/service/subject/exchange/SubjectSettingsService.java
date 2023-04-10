package cz.honestcity.service.subject.exchange;

import cz.honestcity.model.exchange.SubjectSettings;
import org.springframework.stereotype.Service;

@Service
public class SubjectSettingsService {

    private final SubjectSettingsGateway subjectSettingsGateway;

    public SubjectSettingsService(SubjectSettingsGateway subjectSettingsGateway) {
        this.subjectSettingsGateway = subjectSettingsGateway;
    }

    public SubjectSettings get(){
        return subjectSettingsGateway.get();
    }
}
