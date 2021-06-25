package cz.honestcity.endpoints.configuration.login;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import cz.honestcity.model.login.FacebookLoginData;

import java.io.IOException;

/**
 * @author michal.keder
 */
public class FacebookLoginDataDeserializer extends JsonDeserializer<FacebookLoginData> {

    @Override
    public FacebookLoginData deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return new FacebookLoginData()
                .setAccessToken(node.get("accessToken").asText());
    }
}
