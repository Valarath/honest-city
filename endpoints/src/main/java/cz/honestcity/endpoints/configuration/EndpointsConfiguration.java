package cz.honestcity.endpoints.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cz.honestcity.endpoints.configuration.deserializers.*;
import cz.honestcity.endpoints.configuration.serializer.LocalDateSerializer;
import cz.honestcity.model.login.LoginData;
import cz.honestcity.model.subject.WatchedSubject;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.vote.Vote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@ComponentScan(basePackages = "cz.honestcity.endpoints")
@Configuration
public class EndpointsConfiguration {

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(getFacebookLoginDataModule(objectMapper));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    private SimpleModule getFacebookLoginDataModule(ObjectMapper objectMapper){
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(LoginData.class,new LoginDataDeserializer(objectMapper));
        //simpleModule.addDeserializer(Vote.class,new VoteDeserializer(objectMapper));
        simpleModule.addDeserializer(Suggestion.class,new SuggestionDeserializer(objectMapper));
        simpleModule.addDeserializer(WatchedSubject.class,new WatchedSubjectDeserializer(objectMapper));
        simpleModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(objectMapper));
        simpleModule.addSerializer(LocalDate.class,new LocalDateSerializer(objectMapper));
        return simpleModule;
    }
}
