package team.hdt.neutronia_revamped.recipes;

import com.google.gson.JsonObject;

import java.util.function.BooleanSupplier;

public interface IConditionFactory {
    BooleanSupplier parse(JsonContext context, JsonObject json);
}