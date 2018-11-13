package cz.honestcity.model.user;

import cz.honestcity.model.subject.HonestyStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserFilter {
private int areaRange=500;
private HonestyStatus honestyStatus;
}
