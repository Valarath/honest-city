package cz.honestcity.endpoints.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cz.honestcity.endpoints.configuration.login.LoginDataDeserializer;
import cz.honestcity.endpoints.configuration.login.SuggestionDeserializer;
import cz.honestcity.endpoints.configuration.login.VoteDeserializer;
import cz.honestcity.endpoints.configuration.login.WatchedSubjectDeserializer;
import cz.honestcity.model.login.LoginData;
import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.Vote;
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
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    private SimpleModule getFacebookLoginDataModule(){
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(LoginData.class,new LoginDataDeserializer());
        simpleModule.addDeserializer(Vote.class,new VoteDeserializer());
        simpleModule.addDeserializer(Suggestion.class,new SuggestionDeserializer());
        simpleModule.addDeserializer(WatchedSubject.class,new WatchedSubjectDeserializer());
        return simpleModule;
    }
}
