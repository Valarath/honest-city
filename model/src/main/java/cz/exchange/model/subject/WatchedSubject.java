package cz.exchange.model.subject;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WatchedSubject {
    private HonestyStatus honestyStatus;
    private String name;
}
