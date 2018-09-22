package net.hdt.neutronia.groups.decoration.features;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.huskylib2.recipe.BlacklistOreIngredient;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockCustomBookshelf;
import net.hdt.neutronia.properties.EnumVanillaWoodTypes;
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

public class VariedBookshelves extends Component {

    private static BlockMod acacia_bookshelf;
    private static BlockMod birch_bookshelf;
    private static BlockMod dark_oak_bookshelf;
    private static BlockMod jungle_bookshelf;
    private static BlockMod spruce_bookshelf;

    private boolean renameVanillaBookshelves;

    @Override
    public void setupConfig() {
        renameVanillaBookshelves = loadProperty("Rename vanilla bookshelves to Oak Bookshelf", true).get();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (renameVanillaBookshelves)
            Blocks.BOOKSHELF.setTranslationKey("oak_bookshelf");

        acacia_bookshelf = new BlockCustomBookshelf(EnumVanillaWoodTypes.ACACIA);
        birch_bookshelf = new BlockCustomBookshelf(EnumVanillaWoodTypes.BIRCH);
        dark_oak_bookshelf = new BlockCustomBookshelf(EnumVanillaWoodTypes.DARK_OAK);
        jungle_bookshelf = new BlockCustomBookshelf(EnumVanillaWoodTypes.JUNGLE);
        spruce_bookshelf = new BlockCustomBookshelf(EnumVanillaWoodTypes.SPRUCE);

        List<ResourceLocation> recipeList = new ArrayList<>(CraftingManager.REGISTRY.getKeys());
        for (ResourceLocation res : recipeList) {
            IRecipe recipe = CraftingManager.REGISTRY.getObject(res);
            ItemStack out = recipe.getRecipeOutput();
            if (recipe instanceof ShapedRecipes && !out.isEmpty() && (out.getItem() == Item.getItemFromBlock(Blocks.BOOKSHELF))) {
                ShapedRecipes shaped = (ShapedRecipes) recipe;
                NonNullList<Ingredient> ingredients = shaped.recipeItems;
                for (int i = 0; i < ingredients.size(); i++) {
                    Ingredient ingr = ingredients.get(i);
                    if (ingr.apply(ProxyRegistry.newStack(Blocks.PLANKS)))
                        ingredients.set(i, Ingredient.fromStacks(ProxyRegistry.newStack(Blocks.PLANKS, 1, 0)));
                }
            }
        }

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(acacia_bookshelf, 1, 0),
                "WWW", "BBB", "WWW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 4),
                'B', ProxyRegistry.newStack(Items.BOOK));

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(birch_bookshelf, 1, 0),
                "WWW", "BBB", "WWW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 2),
                'B', ProxyRegistry.newStack(Items.BOOK));

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(dark_oak_bookshelf, 1, 0),
                "WWW", "BBB", "WWW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 5),
                'B', ProxyRegistry.newStack(Items.BOOK));

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(jungle_bookshelf, 1, 0),
                "WWW", "BBB", "WWW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 3),
                'B', ProxyRegistry.newStack(Items.BOOK));

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(spruce_bookshelf, 1, 0),
                "WWW", "BBB", "WWW",
                'W', ProxyRegistry.newStack(Blocks.PLANKS, 1, 1),
                'B', ProxyRegistry.newStack(Items.BOOK));

        Ingredient wood = new BlacklistOreIngredient("plankWood", (stack) -> stack.getItem() == Item.getItemFromBlock(Blocks.PLANKS));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(Blocks.BOOKSHELF),
                "WWW", "BBB", "WWW",
                'W', wood,
                'B', ProxyRegistry.newStack(Items.BOOK));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        
        OreDictionary.registerOre("bookshelf", Blocks.BOOKSHELF);

        OreDictionary.registerOre("bookshelfOak", Blocks.BOOKSHELF);
        OreDictionary.registerOre("bookshelfSpruce", ProxyRegistry.newStack(spruce_bookshelf, 1, 0));
        OreDictionary.registerOre("bookshelfBirch", ProxyRegistry.newStack(birch_bookshelf, 1, 0));
        OreDictionary.registerOre("bookshelfJungle", ProxyRegistry.newStack(jungle_bookshelf, 1, 0));
        OreDictionary.registerOre("bookshelfAcacia", ProxyRegistry.newStack(acacia_bookshelf, 1, 0));
        OreDictionary.registerOre("bookshelfDarkOak", ProxyRegistry.newStack(dark_oak_bookshelf, 1, 0));
    }

    @Override
    public String getDescription() {
        return "Adds more types of bookshelf";
    }
    
}