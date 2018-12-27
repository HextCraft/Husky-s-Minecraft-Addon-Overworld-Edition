package team.hdt.neutronia.recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public interface IIngredientFactory
{
    @Nonnull //If you would return null throw JsonSyntaxException to explain why
    Ingredient parse(JsonContext context, JsonObject json);
}