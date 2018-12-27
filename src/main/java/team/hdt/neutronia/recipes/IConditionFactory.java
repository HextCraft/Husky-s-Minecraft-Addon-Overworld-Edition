package team.hdt.neutronia.recipes;

import com.google.gson.JsonObject;

import java.util.function.BooleanSupplier;

public interface IConditionFactory {
    BooleanSupplier parse(JsonContext context, JsonObject json);
}