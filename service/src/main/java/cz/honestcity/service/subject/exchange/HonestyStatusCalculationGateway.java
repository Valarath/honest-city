package cz.honestcity.service.subject.exchange;

import cz.honestcity.model.exchange.Rate;
import cz.honestcity.model.subject.HonestyStatus;

public interface HonestyStatusCalculationGateway {
    HonestyStatus calculate(Rate subjectUsdRate, Rate authorityUsdRate, HonestyStatus currentState);
}
