package team.hdt.neutronia_legacy.groups.building.features;

import team.hdt.huskylib.block.BlockMod;
import team.hdt.huskylib.recipe.RecipeHandler;
import team.hdt.huskylib.util.ProxyRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.base.groups.GlobalConfig;
import team.hdt.neutronia_legacy.base.handler.server.ModIntegrationHandler;
import team.hdt.neutronia_legacy.groups.building.blocks.BlockWorldStoneBricks;

public class WorldStoneBricks extends Component {

    public static BlockMod world_stone_bricks;

    boolean enableStairsAndSlabs;
    boolean enableWalls;

    @Override
    public void setupConfig() {
        enableStairsAndSlabs = loadPropBool("Enable stairs and slabs", "", true) && GlobalConfig.enableVariants;
        enableWalls = loadPropBool("Enable walls", "", true) && GlobalConfig.enableVariants;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        world_stone_bricks = new BlockWorldStoneBricks();

        if (enableStairsAndSlabs) {
            for (BlockWorldStoneBricks.Variants variant : BlockWorldStoneBricks.Variants.class.getEnumConstants()) {
                if (!variant.isEnabled())
                    continue;

                String name = variant.getName();
                VanillaStairsAndSlabs.add(name, world_stone_bricks, variant.ordinal(), true);
            }
        }

        if (enableWalls)
            for (BlockWorldStoneBricks.Variants variant : BlockWorldStoneBricks.Variants.class.getEnumConstants()) {
                if (!variant.isEnabled())
                    continue;

                world_stone_bricks.getDefaultState().withProperty(world_stone_bricks.getVariantProp(), variant);
                String name = variant.getName();
                VanillaWalls.add(name, world_stone_bricks, variant.ordinal(), true);
            }
    }

    @Override
    public void postPreInit(FMLPreInitializationEvent event) {
        for (int i = 0; i < 6; i++)
            RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(world_stone_bricks, 4, i),
                    "SS", "SS",
                    'S', ProxyRegistry.newStack(Blocks.STONE, 1, i * 2 + 2));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ModIntegrationHandler.registerChiselVariant("granite", ProxyRegistry.newStack(world_stone_bricks, 1, 0));
        ModIntegrationHandler.registerChiselVariant("diorite", ProxyRegistry.newStack(world_stone_bricks, 1, 1));
        ModIntegrationHandler.registerChiselVariant("andesite", ProxyRegistry.newStack(world_stone_bricks, 1, 2));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
