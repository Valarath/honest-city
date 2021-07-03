package cz.honestcity.endpoints.configuration.login;

import cz.honestcity.model.subject.WatchedSubject;

/**
 * @author michal.keder
 */
public class WatchedSubjectDeserializer extends DataDeserializer<WatchedSubject>{

    public WatchedSubjectDeserializer() {
        super(WatchedSubject.class);
    }

    public WatchedSubjectDeserializer(String packagePath) {
        super(WatchedSubject.class, packagePath);
    }
}
