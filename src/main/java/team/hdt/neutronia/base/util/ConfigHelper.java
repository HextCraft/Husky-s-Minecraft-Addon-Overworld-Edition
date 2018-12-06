package team.hdt.neutronia.base.util;

import com.google.gson.JsonElement;

class ConfigHelper {
    static boolean isString(JsonElement element) {
        return isPrimitive(element) && element.getAsJsonPrimitive().isString();
    }

    static boolean isInt(JsonElement element) {
        return isPrimitive(element) && element.getAsJsonPrimitive().isNumber();
    }

    static boolean isFloat(JsonElement element) {
        return isPrimitive(element) && element.getAsJsonPrimitive().isNumber();
    }

    static boolean isBoolean(JsonElement element) {
        return isPrimitive(element) && element.getAsJsonPrimitive().isBoolean();
    }

    private static boolean isPrimitive(JsonElement element) {
        return isNull(element) && element.isJsonPrimitive();
    }

    static boolean isObject(JsonElement element) {
        return isNull(element) && element.isJsonObject();
    }

    static boolean isArray(JsonElement element) {
        return isNull(element) && element.isJsonArray();
    }

    private static boolean isNull(JsonElement element) {
        return element != null && !element.isJsonNull();
    }
}