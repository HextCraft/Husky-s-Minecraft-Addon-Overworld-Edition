package net.hdt.neutronia.groups.decoration.features;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.huskylib2.recipe.BlacklistOreIngredient;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockCustomLadder;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VariedLadders extends Component {

    public static BlockMod acacia_ladder, birch_ladder, dark_oak_ladder, jungle_ladder, spruce_ladder;

    private boolean renameVanillaLadder;

    @Override
    public void setupConfig() {
        renameVanillaLadder = loadPropBool("Rename vanilla ladder to Oak Ladder", "", true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (renameVanillaLadder)
            Blocks.LADDER.setTranslationKey("oak_ladder");

        acacia_ladder = new BlockCustomLadder("acacia");
        birch_ladder = new BlockCustomLadder("birch");
        dark_oak_ladder = new BlockCustomLadder("dark_oak");
        jungle_ladder = new BlockCustomLadder("jungle");
        spruce_ladder = new BlockCustomLadder("spruce");

        List<ResourceLocation> recipeList = new ArrayList<>(CraftingManager.REGISTRY.getKeys());
        for (ResourceLocation res : recipeList) {
            IRecipe recipe = CraftingManager.REGISTRY.getObject(res);
            ItemStack out = Objects.requireNonNull(recipe).getRecipeOutput();
            if (recipe instanceof ShapedRecipes && !out.isEmpty() && (out.getItem() == Item.getItemFromBlock(Blocks.LADDER))) {
                ShapedRecipes shaped = (ShapedRecipes) recipe;
                NonNullList<Ingredient> ingredients = shaped.recipeItems;
                for (int i = 0; i < ingredients.size(); i++) {
                    Ingredient ingr = ingredients.get(i);
                    if (ingr.apply(ProxyRegistry.newStack(Blocks.PLANKS)))
                        ingredients.set(i, Ingredient.fromStacks(ProxyRegistry.newStack(Blocks.PLANKS, 1, 0)));
                }
            }
        }

        Ingredient wood = new BlacklistOreIngredient("plankWood", (stack) -> stack.getItem() == Item.getItemFromBlock(Blocks.PLANKS));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(Blocks.LADDER),
                "S S", "SWS", "S S",
                'W', wood,
                'S', ProxyRegistry.newStack(Items.STICK));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        OreDictionary.registerOre("ladder", Blocks.LADDER);

        OreDictionary.registerOre("ladderOak", Blocks.LADDER);
        OreDictionary.registerOre("ladderSpruce", ProxyRegistry.newStack(spruce_ladder, 1, 0));
        OreDictionary.registerOre("ladderBirch", ProxyRegistry.newStack(birch_ladder, 1, 0));
        OreDictionary.registerOre("ladderJungle", ProxyRegistry.newStack(jungle_ladder, 1, 0));
        OreDictionary.registerOre("ladderAcacia", ProxyRegistry.newStack(acacia_ladder, 1, 0));
        OreDictionary.registerOre("ladderDarkOak", ProxyRegistry.newStack(dark_oak_ladder, 1, 0));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}