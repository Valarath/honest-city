package cz.honestcity.endpoints.configuration.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.honestcity.model.subject.WatchedSubject;

/**
 * @author michal.keder
 */
public class WatchedSubjectDeserializer extends DataDeserializer<WatchedSubject>{

    public WatchedSubjectDeserializer(ObjectMapper objectMapper) {
        super(objectMapper, WatchedSubject.class);
    }

    public WatchedSubjectDeserializer(ObjectMapper objectMapper,String packagePath) {
        super(objectMapper, WatchedSubject.class, packagePath);
    }
}
