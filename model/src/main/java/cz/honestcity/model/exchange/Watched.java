package cz.honestcity.model.exchange;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class Watched {
    private LocalDate from;
    private LocalDate to;
}
