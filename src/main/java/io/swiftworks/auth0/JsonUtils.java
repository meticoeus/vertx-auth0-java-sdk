package io.swiftworks.auth0;

import io.vertx.core.json.JsonObject;

import java.util.Map;

import static com.google.common.base.CaseFormat.*;

public class JsonUtils {
    public static JsonObject toCamelCase(JsonObject input) {
        JsonObject output = new JsonObject();

        for (Map.Entry<String, Object> entry : input.getMap().entrySet()) {
            output.put(LOWER_UNDERSCORE.to(LOWER_CAMEL, entry.getKey()), entry.getValue());
        }

        return output;
    }

    public static <T> void addIfNotNull(JsonObject o, String prop, T value) {
        if (value != null) {
            o.put(prop, o);
        }
    }
}
