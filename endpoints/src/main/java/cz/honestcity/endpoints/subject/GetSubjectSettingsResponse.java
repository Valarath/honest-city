package cz.honestcity.endpoints.subject;

import cz.honestcity.model.exchange.SubjectSettings;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetSubjectSettingsResponse {

    private SubjectSettings subjectSettings;
}
