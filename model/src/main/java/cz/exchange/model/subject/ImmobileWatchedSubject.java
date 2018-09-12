package cz.exchange.model.subject;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ImmobileWatchedSubject extends WatchedSubject{
    private double longitude;
    private double latitude;
}
