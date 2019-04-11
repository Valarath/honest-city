package cz.honestcity.service.subject;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.subject.WatchedSubject;

import java.util.List;

public abstract class SubjectService {

    public abstract void createSubject(WatchedSubject subject);

    public abstract List<? extends WatchedSubject> getSubjectsInArea(Position position);
}
