package cz.honestcity.calculation.status;

import cz.honestcity.model.exchange.Rate;
import cz.honestcity.model.subject.HonestyStatus;
import cz.honestcity.service.subject.exchange.HonestyStatusCalculationGateway;

public class LinearHonestyStatusCalculationService implements HonestyStatusCalculationGateway {

    @Override
    public HonestyStatus calculate(Rate subjectUsdRate, Rate authorityUsdRate, HonestyStatus currentState) {
        if (isRateHonest(authorityUsdRate, subjectUsdRate))
            return HonestyStatus.DISHONEST;
        else
            return getHonestStatus(currentState);
    }

    private HonestyStatus getHonestStatus(HonestyStatus currentState) {
        HonestyStatus nextLevelOfHonesty = currentState.getNextLevelOfHonesty();
        return nextLevelOfHonesty == null ? HonestyStatus.HONEST : nextLevelOfHonesty;
    }

    private boolean isRateHonest(Rate centralAuthorityRate, Rate exchangePointRate) {
        return exchangePointRate == null || ((1 - exchangePointRate.getRateValues().getBuy() / centralAuthorityRate.getRateValues().getBuy()) * 100 > 10);
    }

}
