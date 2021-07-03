package cz.honestcity.model.subject;


import cz.honestcity.model.HonestCitySerializable;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class WatchedSubject extends HonestCitySerializable {
    private String id;
    private LocalDate watchedTo;
    private HonestyStatus honestyStatus;
}
