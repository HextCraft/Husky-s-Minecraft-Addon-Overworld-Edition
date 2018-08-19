package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.huskylib2.block.BlockModSlab;
import net.hdt.huskylib2.block.BlockModStairs;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GlobalConfig;
import net.hdt.neutronia.groups.building.features.VanillaWalls;
import net.hdt.neutronia.groups.world.blocks.BlockBasalt;
import net.hdt.neutronia.groups.world.blocks.slab.BlockBasaltSlab;
import net.hdt.neutronia.groups.world.blocks.stairs.BlockBasaltStairs;
import net.hdt.neutronia.groups.world.features.overworld.BetterStoneGeneration.StoneInfo;
import net.hdt.neutronia.groups.world.world.BasaltGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class Basalt extends Component {

    public static BlockMod basalt;

    StoneInfo basaltInfo;

    boolean enableStairsAndSlabs;
    boolean enableWalls;

    @Override
    public void setupConfig() {
        basaltInfo = BetterStoneGeneration.loadStoneInfo(configCategory, "basalt", 18, 20, 120, 20, true, "-1", BiomeDictionary.Type.NETHER);
        enableStairsAndSlabs = loadPropBool("Enable stairs and slabs", "", true) && GlobalConfig.enableVariants;
        enableWalls = loadPropBool("Enable walls", "", true) && GlobalConfig.enableVariants;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        basalt = new BlockBasalt();

        if (enableStairsAndSlabs) {
            BlockModSlab.initSlab(basalt, 0, new BlockBasaltSlab(false), new BlockBasaltSlab(true));
            BlockModStairs.initStairs(basalt, 0, new BlockBasaltStairs());
        }
        VanillaWalls.add("basalt", basalt, 0, enableWalls);

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(basalt, 4, 1),
                "BB", "BB",
                'B', ProxyRegistry.newStack(basalt, 1, 0));

        GameRegistry.registerWorldGenerator(new BasaltGenerator(() -> basaltInfo), 0);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        OreDictionary.registerOre("stoneBasalt", ProxyRegistry.newStack(basalt, 1, 0));
        OreDictionary.registerOre("stoneBasaltPolished", ProxyRegistry.newStack(basalt, 1, 1));
    }

    @Override
    public void postPreInit(FMLPreInitializationEvent event) {
        ItemStack blackItem = ProxyRegistry.newStack(Items.COAL);

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(basalt, 4, 0),
                "BI", "IB",
                'B', ProxyRegistry.newStack(Blocks.COBBLESTONE, 1, 0),
                'I', blackItem);
        RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(Blocks.STONE, 1, 5), ProxyRegistry.newStack(basalt), ProxyRegistry.newStack(Items.QUARTZ));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
