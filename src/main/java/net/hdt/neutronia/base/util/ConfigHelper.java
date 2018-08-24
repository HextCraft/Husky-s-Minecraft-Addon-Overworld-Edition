package net.hdt.neutronia.base.util;

import com.google.gson.JsonElement;

public class ConfigHelper {
    public static boolean isString(JsonElement element) {
        return isPrimitive(element) && element.getAsJsonPrimitive().isString();
    }

    public static boolean isInt(JsonElement element) {
        return isPrimitive(element) && element.getAsJsonPrimitive().isNumber();
    }

    public static boolean isFloat(JsonElement element) {
        return isPrimitive(element) && element.getAsJsonPrimitive().isNumber();
    }

    public static boolean isBoolean(JsonElement element) {
        return isPrimitive(element) && element.getAsJsonPrimitive().isBoolean();
    }

    public static boolean isPrimitive(JsonElement element) {
        return !isNull(element) && element.isJsonPrimitive();
    }

    public static boolean isObject(JsonElement element) {
        return !isNull(element) && element.isJsonObject();
    }

    public static boolean isArray(JsonElement element) {
        return !isNull(element) && element.isJsonArray();
    }

    public static boolean isNull(JsonElement element) {
        return element == null || element.isJsonNull();
    }
}