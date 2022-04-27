package cz.honestcity.endpoints.subject;

import cz.honestcity.model.subject.Position;
import lombok.Data;

@Data
public class GetSubjectsRequest {
    private Position userPosition;
}
