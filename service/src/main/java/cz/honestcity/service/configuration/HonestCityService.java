package cz.honestcity.service.configuration;


import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface HonestCityService {

    Class beanId();
}
