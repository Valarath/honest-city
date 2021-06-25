package cz.honestcity.service.configuration;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author michal.keder
 */
@Service
public class IdProvider {

    public String provideNewId(){
        return UUID.randomUUID().toString();
    }

}
