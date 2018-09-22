package net.hdt.neutronia.api.modules.impl;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

public interface RecipeAdder {
    void registerRecipes(RegistryEvent.Register<IRecipe> event);
}
