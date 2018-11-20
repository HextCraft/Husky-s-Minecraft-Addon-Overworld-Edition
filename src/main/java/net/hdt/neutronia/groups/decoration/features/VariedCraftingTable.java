package net.hdt.neutronia.groups.decoration.features;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockCustomCraftingTable;
import net.hdt.neutronia.properties.EnumVanillaWoodTypes;
import net.minecraft.init.Blocks;
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

public class VariedCraftingTable extends Component {

    public static BlockMod acacia_crafting_table, birch_crafting_table, dark_oak_crafting_table, jungle_crafting_table, spruce_crafting_table;

    private boolean renameVanillaCraftingTable;

    @Override
    public void setupConfig() {
        renameVanillaCraftingTable = loadPropBool("Rename vanilla bookshelves to Oak Crafting Table", "", true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (renameVanillaCraftingTable)
            Blocks.CRAFTING_TABLE.setTranslationKey("oak_crafting_table");

        acacia_crafting_table = new BlockCustomCraftingTable(EnumVanillaWoodTypes.ACACIA);
        birch_crafting_table = new BlockCustomCraftingTable(EnumVanillaWoodTypes.BIRCH);
        dark_oak_crafting_table = new BlockCustomCraftingTable(EnumVanillaWoodTypes.DARK_OAK);
        jungle_crafting_table = new BlockCustomCraftingTable(EnumVanillaWoodTypes.JUNGLE);
        spruce_crafting_table = new BlockCustomCraftingTable(EnumVanillaWoodTypes.SPRUCE);

        List<ResourceLocation> recipeList = new ArrayList<>(CraftingManager.REGISTRY.getKeys());
        for (ResourceLocation res : recipeList) {
            IRecipe recipe = CraftingManager.REGISTRY.getObject(res);
            ItemStack out = recipe.getRecipeOutput();
            if (recipe instanceof ShapedRecipes && !out.isEmpty() && (out.getItem() == Item.getItemFromBlock(Blocks.CRAFTING_TABLE))) {
                ShapedRecipes shaped = (ShapedRecipes) recipe;
                NonNullList<Ingredient> ingredients = shaped.recipeItems;
                for (int i = 0; i < ingredients.size(); i++) {
                    Ingredient ingr = ingredients.get(i);
                    if (ingr.apply(ProxyRegistry.newStack(Blocks.PLANKS)))
                        ingredients.set(i, Ingredient.fromStacks(ProxyRegistry.newStack(Blocks.PLANKS, 1, 0)));
                }
            }
        }

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(acacia_crafting_table, 1, 0),
                "WW", "WW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 4));

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(birch_crafting_table, 1, 0),
                "WW", "WW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 2));

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(dark_oak_crafting_table, 1, 0),
                "WW", "WW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 5));

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(jungle_crafting_table, 1, 0),
                "WW", "WW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 3));

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(spruce_crafting_table, 1, 0),
                "WW", "WW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 1));

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(Blocks.CRAFTING_TABLE),
                "WW", "WW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 0));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        OreDictionary.registerOre("craftingTable", Blocks.CRAFTING_TABLE);

        OreDictionary.registerOre("craftingTableOak", Blocks.CRAFTING_TABLE);
        OreDictionary.registerOre("craftingTableSpruce", ProxyRegistry.newStack(spruce_crafting_table, 1, 0));
        OreDictionary.registerOre("craftingTableBirch", ProxyRegistry.newStack(birch_crafting_table, 1, 0));
        OreDictionary.registerOre("craftingTableJungle", ProxyRegistry.newStack(jungle_crafting_table, 1, 0));
        OreDictionary.registerOre("craftingTableAcacia", ProxyRegistry.newStack(acacia_crafting_table, 1, 0));
        OreDictionary.registerOre("craftingTableDarkOak", ProxyRegistry.newStack(dark_oak_crafting_table, 1, 0));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}