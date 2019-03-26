package cz.honestcity.endpoints.exchange;

import cz.honestcity.model.subject.Position;
import cz.honestcity.model.user.UserFilter;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetExchangesRequest {
	private Position userPosition;
	private UserFilter userFilter;
}
