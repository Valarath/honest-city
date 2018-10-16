package cz.honestcity.service.gateway;

import java.time.LocalDate;

public interface RateGateway {

	Object getRate(LocalDate day);
}
