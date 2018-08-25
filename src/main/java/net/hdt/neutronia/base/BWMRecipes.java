package net.hdt.neutronia.base;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import java.util.*;

public final class BWMRecipes {
    private static final List<ItemStack> REMOVE_RECIPE_BY_OUTPUT = Lists.newArrayList();
    private static final List<ResourceLocation> REMOVE_RECIPE_BY_RL = Lists.newArrayList();
    private static final List<IRecipe> RECIPES = new ArrayList<>();
    private static final Map<String, List<IRecipe>> HARDCORE_RECIPES = new HashMap<>();

    public static void addHardcoreRecipe(String ID, IRecipe recipe) {
        if (!HARDCORE_RECIPES.containsKey(ID)) {
            HARDCORE_RECIPES.put(ID, new ArrayList<>());
        }
        HARDCORE_RECIPES.get(ID).add(recipe);
    }

    public static List<IRecipe> getRecipes() {
        return Collections.unmodifiableList(RECIPES);
    }

    public static void removeRecipe(ItemStack output) {
        REMOVE_RECIPE_BY_OUTPUT.add(output);
    }

    private static void removeRecipe(ResourceLocation loc) {
        REMOVE_RECIPE_BY_RL.add(loc);
    }

    public static void removeRecipe(String loc) {
        removeRecipe(new ResourceLocation(loc));
    }

}
