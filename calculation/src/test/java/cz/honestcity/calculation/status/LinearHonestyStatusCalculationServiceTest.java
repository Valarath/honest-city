package cz.honestcity.calculation.status;

import cz.honestcity.model.exchange.Currency;
import cz.honestcity.model.exchange.ExchangeRateValues;
import cz.honestcity.model.exchange.Rate;
import cz.honestcity.model.subject.HonestyStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinearHonestyStatusCalculationServiceTest {

    private final LinearHonestyStatusCalculationService service = new LinearHonestyStatusCalculationService();

    @Test
    public void statusIsHonestForNewHonestSubject(){
        Rate authorityRate = getAuthorityRate();
        HonestyStatus result = service.calculate(getHonestSubjectRate(authorityRate), authorityRate,HonestyStatus.UNKNOWN);
        Assertions.assertEquals(result,HonestyStatus.HONEST);

        result = service.calculate(getDishonestSubjectRate(), authorityRate,HonestyStatus.UNKNOWN);
        Assertions.assertEquals(result,HonestyStatus.DISHONEST);
    }

    @Test
    public void statusIsDishonestForDishonestSubject(){
        Rate authorityRate = getAuthorityRate();
        HonestyStatus result = service.calculate(getDishonestSubjectRate(), authorityRate,HonestyStatus.HONEST);
        Assertions.assertEquals(result,HonestyStatus.DISHONEST);
    }

    @Test
    public void statusIsImprovedIfSubjectIsHonest(){
        Rate authorityRate = getAuthorityRate();
        HonestyStatus result = service.calculate(getHonestSubjectRate(authorityRate), authorityRate,HonestyStatus.DISHONEST);
        Assertions.assertEquals(result,HonestyStatus.DISHONEST.getNextLevelOfHonesty());
    }

    private Rate getHonestSubjectRate(Rate authorityRate){
        double buy = authorityRate.getRateValues().getBuy();
        return new Rate()
                .setCurrency(Currency.USD)
                .setRateValues(new ExchangeRateValues().setBuy(buy));
    }

    private Rate getDishonestSubjectRate(){
        return new Rate()
                .setCurrency(Currency.USD)
                .setRateValues(new ExchangeRateValues().setBuy(0));
    }

    private Rate getAuthorityRate(){
        return new Rate()
                .setCurrency(Currency.USD)
                .setRateValues(new ExchangeRateValues().setBuy(25));
    }

}