package cz.honestcity.database.subject;

import cz.honestcity.model.exchange.SubjectSettings;
import cz.honestcity.service.subject.exchange.SubjectSettingsGateway;
import org.springframework.stereotype.Service;

@Service
public class SubjectSettingsPostgresGateway implements SubjectSettingsGateway {

    private final SubjectSettingsMapper subjectSettingsMapper;

    public SubjectSettingsPostgresGateway(SubjectSettingsMapper subjectSettingsMapper) {
        this.subjectSettingsMapper = subjectSettingsMapper;
    }

    @Override
    public SubjectSettings get(){
        return new SubjectSettings()
                .setNames(subjectSettingsMapper.get());
    }
}
