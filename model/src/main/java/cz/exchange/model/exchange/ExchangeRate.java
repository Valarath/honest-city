package cz.exchange.model.exchange;

import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ExchangeRate {
    private LocalDateTime validFor;
    private Set<Rate> rates;
    private Image proofPicture;
}
