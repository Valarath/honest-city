package cz.exchange.model.exchange;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Currency;

@Data
@Accessors(chain = true)
public class Rate {
    private String from;
    private String to;
    private int get;
}
