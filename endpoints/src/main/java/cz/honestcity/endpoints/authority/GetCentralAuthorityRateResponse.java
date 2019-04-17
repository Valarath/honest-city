package cz.honestcity.endpoints.authority;

import cz.honestcity.model.exchange.ExchangeRate;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetCentralAuthorityRateResponse {
    private ExchangeRate exchangeRate;
}
