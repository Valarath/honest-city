package cz.honestcity.model.exchange;

import cz.honestcity.model.HonestCitySerializable;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class Watched extends HonestCitySerializable {
    private LocalDate from;
    private LocalDate to;
}
