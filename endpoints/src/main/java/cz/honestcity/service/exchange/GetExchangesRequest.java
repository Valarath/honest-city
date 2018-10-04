package cz.honestcity.service.exchange;

import cz.honestcity.model.subject.Position;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetExchangesRequest {
	private Position userPosition;
}
