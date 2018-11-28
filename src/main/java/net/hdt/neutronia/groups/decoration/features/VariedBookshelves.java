package net.hdt.neutronia.groups.decoration.features;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.huskylib2.recipe.BlacklistOreIngredient;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.handler.server.RecipeProcessor;
import net.hdt.neutronia.groups.decoration.blocks.BlockCustomBookshelf;
import net.hdt.neutronia.properties.EnumVanillaWoodTypes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

public class VariedBookshelves extends Component {

    public static BlockMod acacia_bookshelf, birch_bookshelf, dark_oak_bookshelf, jungle_bookshelf, spruce_bookshelf, cherry_bookshelf, baobab_bookshelf, willow_bookshelf, palm_bookshelf, purhogany_bookshelf;

    private boolean renameVanillaBookshelves;

    @Override
    public void setupConfig() {
        renameVanillaBookshelves = loadPropBool("Rename vanilla bookshelves to Oak Bookshelf", "", true);
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
        /*cherry_bookshelf = new BlockCustomBookshelf(EnumVanillaWoodTypes.SPRUCE);
        baobab_bookshelf = new BlockCustomBookshelf(EnumVanillaWoodTypes.SPRUCE);
        willow_bookshelf = new BlockCustomBookshelf(EnumVanillaWoodTypes.SPRUCE);
        palm_bookshelf = new BlockCustomBookshelf(EnumVanillaWoodTypes.SPRUCE);
        purhogany_bookshelf = new BlockCustomBookshelf(EnumVanillaWoodTypes.SPRUCE);*/

        RecipeProcessor.addWoodReplacements(Blocks.BOOKSHELF);

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
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}