package rate;

import cz.honestcity.service.gateway.RateDatabaseGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RatePossgressGateway implements RateDatabaseGateway {

    @Autowired
    private RateMapper rateMapper;

    @Override
    public Object getRate(LocalDate day) {
        return rateMapper.getRate(day);
    }
}
