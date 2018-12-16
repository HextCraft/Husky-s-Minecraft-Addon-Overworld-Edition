package team.hdt.neutronia_legacy.groups.decoration.features;

import team.hdt.huskylib.block.BlockMod;
import team.hdt.huskylib.recipe.RecipeHandler;
import team.hdt.huskylib.util.ProxyRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.base.handler.server.RecipeProcessor;
import team.hdt.neutronia_legacy.groups.decoration.blocks.BlockCustomCraftingTable;
import team.hdt.neutronia_legacy.properties.EnumVanillaWoodTypes;

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

        RecipeProcessor.addWoodReplacements(1, Blocks.CRAFTING_TABLE);

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