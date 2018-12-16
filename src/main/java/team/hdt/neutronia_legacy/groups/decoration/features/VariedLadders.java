package team.hdt.neutronia_legacy.groups.decoration.features;

import team.hdt.huskylib.block.BlockMod;
import team.hdt.huskylib.recipe.BlacklistOreIngredient;
import team.hdt.huskylib.recipe.RecipeHandler;
import team.hdt.huskylib.util.ProxyRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.base.handler.server.RecipeProcessor;
import team.hdt.neutronia_legacy.groups.decoration.blocks.BlockCustomLadder;

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

        RecipeProcessor.addWoodReplacements(6, Blocks.LADDER);

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