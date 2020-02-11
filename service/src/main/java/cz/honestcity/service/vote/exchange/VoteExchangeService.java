package cz.honestcity.service.vote.exchange;

import cz.honestcity.service.subject.exchange.ExchangeService;
import cz.honestcity.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class VoteExchangeService extends VoteService {

    @Autowired
    protected ExchangeService exchangeService;

}
