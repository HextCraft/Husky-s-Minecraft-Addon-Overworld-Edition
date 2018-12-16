package team.hdt.neutronia_legacy.groups.world.features.overworld;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.base.groups.GroupLoader;
import team.hdt.neutronia_legacy.base.handler.server.DimensionConfig;
import team.hdt.neutronia_legacy.groups.building.features.MoreStoneBlocks;
import team.hdt.neutronia_legacy.groups.world.blocks.BlockStalactite;
import team.hdt.neutronia_legacy.groups.world.world.StalactiteGenerator;

public class Stalactite extends Component {

    public static Block stone_stalactite, granite_stalactite, diorite_stalactite,
            andesite_stalactite, basalt_stalactite, marble_stalactite, limestone_stalactite,
            netherrack_stalactite, ice_stalactite, packed_ice_stalactite, crystal_stalactite,
            end_stalactite, clay_stalactite, dirt_stalactite, sandstone_stalactite;

    public static int tries, clusterCount, netherTries, netherClusterCount, maxHeight;
    public static DimensionConfig dimensionConfig;

    @Override
    public void setupConfig() {
        tries = loadPropInt("Cluster Attempts Per Chunk", "", 60);
        clusterCount = loadPropInt("Stalactite Per Cluster", "", 12);
        netherTries = loadPropInt("Cluster Attempts Per Chunk (Nether)", "", 4);
        netherClusterCount = loadPropInt("Stalactite Per Cluster (Nether)", "", 12);
        maxHeight = loadPropInt("Highest Y Level", "", 55);

        dimensionConfig = new DimensionConfig(configCategory, "0,-1");
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        stone_stalactite = new BlockStalactite("stone", false);
        granite_stalactite = new BlockStalactite("granite", false);
        diorite_stalactite = new BlockStalactite("diorite", false);
        andesite_stalactite = new BlockStalactite("andesite", false);
        netherrack_stalactite = new BlockStalactite("netherrack", false).setNetherrack();
        ice_stalactite = new BlockStalactite("ice", false);
        packed_ice_stalactite = new BlockStalactite("packed_ice", false);
        crystal_stalactite = new BlockStalactite("crystal", true);
        end_stalactite = new BlockStalactite("end_stone", false);
        clay_stalactite = new BlockStalactite("clay", false);
        dirt_stalactite = new BlockStalactite("dirt", false);
        sandstone_stalactite = new BlockStalactite("sandstone", false);

        if (GroupLoader.isFeatureEnabled(MoreStoneBlocks.class)) {
            basalt_stalactite = new BlockStalactite("basalt", false);
            marble_stalactite = new BlockStalactite("marble", false);
            limestone_stalactite = new BlockStalactite("limestone", false);
        }

        GameRegistry.registerWorldGenerator(new StalactiteGenerator(), 1000);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
