package cz.honestcity.model.exchange;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Rate {
    private String from;
    private String to;
    private int get;
}
