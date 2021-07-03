package cz.honestcity.endpoints.configuration.login;

/**
 * @author michal.keder
 */

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class DataDeserializer<DATA> extends JsonDeserializer<DATA> {

    private static final String CLASS_NAME_PROPERTY = "className";

    private final ObjectMapper objectMapper;
    private final Map<String,Class<? extends DATA>> classesByClassNames;

    public DataDeserializer(ObjectMapper objectMapper, Class<DATA> deserializerType) {
        this.objectMapper = objectMapper;
        this.classesByClassNames = getClassesByNames(deserializerType,"cz.honestcity.model");
    }

    public DataDeserializer(ObjectMapper objectMapper, Class<DATA> deserializerType, String packagePath) {
        this.objectMapper = objectMapper;
        this.classesByClassNames = getClassesByNames(deserializerType,packagePath);
    }

    private Map<String, Class<? extends DATA>> getClassesByNames(Class<DATA> deserializerType, String packagePath){
        Reflections reflections = new Reflections(packagePath);
        Set<Class<? extends DATA>> subTypes = reflections.getSubTypesOf(deserializerType);
        return subTypes.stream().collect(Collectors.toMap(Class::getSimpleName, it -> it));
    }

    @Override
    public DATA deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return objectMapper.readValue(node.toString(), getClass(node));
    }

    private Class<? extends DATA> getClass(JsonNode node) {
        return classesByClassNames.get(node.get(CLASS_NAME_PROPERTY).asText());
    }


}
