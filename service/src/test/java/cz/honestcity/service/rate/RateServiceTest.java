package cz.honestcity.service.rate;

import cz.honestcity.model.exchange.ExchangeRate;
import cz.honestcity.service.base.AbstractServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RateServiceTest extends AbstractServiceTest {

    private static final LocalDate NOW = LocalDate.now();

    @InjectMocks
    private RateService service;

    @Mock
    private RateDatabaseGateway rateDatabaseGateway;

    @Mock
    private RateCrawlingGateway rateCrawlingGateway;

    @Mock
    private Map<String, ? extends RateGateway> rateGateways;

    @Before
    public void setup(){
        super.setup();
        prepareCommonEnvironment();
    }

    @Test
    public void crawlRate() {
        prepareEnvironmentForCrawling();
        service.crawlRate();
    }

    private void prepareEnvironmentForCrawling() {
        ExchangeRate exchangeRate = getExchangePointRateForTest();
        when(rateCrawlingGateway.getRate(NOW)).thenReturn(exchangeRate);
        doNothing().when(rateDatabaseGateway).saveCentralAuthorityRate(exchangeRate);
    }

    @Test
    public void getExchangePointRate() {
        ExchangeRate exchangePointRate = getExchangePointRateForTest();
        prepareEnvironmentForGetExchangePointRate(exchangePointRate);
        assertEquals(exchangePointRate,service.getExchangePointRate(EXCHANGE_POINT_ID));
    }

    private void prepareEnvironmentForGetExchangePointRate(ExchangeRate exchangePointRate) {
        when(rateDatabaseGateway.getExchangePointRate(EXCHANGE_POINT_ID)).thenReturn(exchangePointRate);
    }

    @Test
    public void getCentralAuthorityRate() {
        ExchangeRate exchangePointRate = getExchangePointRateForTest();
        prepareEnvironmentForGetCentralAuthorityRate(exchangePointRate);
        assertEquals(exchangePointRate,service.getCentralAuthorityRate());
    }

    private void prepareEnvironmentForGetCentralAuthorityRate(ExchangeRate exchangePointRate) {
        when(rateDatabaseGateway.getCentralAuthorityRate()).thenReturn(exchangePointRate);

    }

    private void prepareCommonEnvironment(){
        doReturn(rateDatabaseGateway).when(rateGateways).get(RateGatewayType.RATE_DATABASE_GATEWAY.name);
        doReturn(rateCrawlingGateway).when(rateGateways).get(RateGatewayType.RATE_CRAWLING_GATEWAY.name);
    }
}