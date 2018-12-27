package team.hdt.neutronia.recipes;

import com.google.gson.JsonObject;

import net.minecraft.item.crafting.IRecipe;

public interface IRecipeFactory {
    IRecipe parse(JsonContext context, JsonObject json);
}