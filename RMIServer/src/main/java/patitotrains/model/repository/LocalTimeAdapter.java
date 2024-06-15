package patitotrains.model.repository;

import com.google.gson.*;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Clase que adapta la serializacion y deserializacion de LocalTime
 */
public class LocalTimeAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime>, Serializable {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Constructor de la clase
     */
    @Override
    public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localTime.format(FORMATTER));
    }

    /**
     * Método que deserializa un objeto
     * @param jsonElement Elemento JSON
     * @param type Tipo
     * @param jsonDeserializationContext Contexto de deserialización
     * @return Objeto deserializado
     */
    @Override
    public LocalTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalTime.parse(jsonElement.getAsString(), FORMATTER);
    }
}
