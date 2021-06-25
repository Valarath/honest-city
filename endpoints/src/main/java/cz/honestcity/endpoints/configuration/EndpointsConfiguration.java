package cz.honestcity.endpoints.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cz.honestcity.endpoints.configuration.login.FacebookLoginDataDeserializer;
import cz.honestcity.model.login.FacebookLoginData;
import cz.honestcity.model.login.LoginData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "cz.honestcity.endpoints")
@Configuration
public class EndpointsConfiguration {

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(getFacebookLoginDataModule());
        return objectMapper;
    }

    private SimpleModule getFacebookLoginDataModule(){
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(LoginData.class,new FacebookLoginDataDeserializer());
        return simpleModule;
    }
}
