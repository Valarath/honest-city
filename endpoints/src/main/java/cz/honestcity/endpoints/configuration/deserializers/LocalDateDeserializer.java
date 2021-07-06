package cz.honestcity.endpoints.configuration.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author michal.keder
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    public LocalDateDeserializer(ObjectMapper objectMapper) {

    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return LocalDate.parse(node.asText());
    }
}
