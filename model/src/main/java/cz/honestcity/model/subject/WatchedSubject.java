package cz.honestcity.model.subject;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class WatchedSubject {
    private String id;
    private LocalDate watchedTo;
    private HonestyStatus honestyStatus;
}
