package cz.honestcity.model.exchange;

import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.*;

@Data
@Accessors(chain = true)
public class ExchangePointRate extends ExchangeRate {
    protected Image proofPicture;
}
