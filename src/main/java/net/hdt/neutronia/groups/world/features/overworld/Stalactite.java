package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.base.handler.server.DimensionConfig;
import net.hdt.neutronia.groups.building.features.MoreStoneBlocks;
import net.hdt.neutronia.groups.world.blocks.BlockStalactite;
import net.hdt.neutronia.groups.world.world.StalactiteGenerator;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Stalactite extends Component {

    public static Block stone_stalactite, granite_stalactite, diorite_stalactite,
            andesite_stalactite, basalt_stalactite, marble_stalactite, limestone_stalactite,
            netherrack_stalactite, ice_stalactite, packed_ice_stalactite;

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
        stone_stalactite = new BlockStalactite("stone");
        granite_stalactite = new BlockStalactite("granite");
        diorite_stalactite = new BlockStalactite("diorite");
        andesite_stalactite = new BlockStalactite("andesite");
        netherrack_stalactite = new BlockStalactite("netherrack").setNetherrack();
        ice_stalactite = new BlockStalactite("ice");
        packed_ice_stalactite = new BlockStalactite("packed_ice");

        if (GroupLoader.isFeatureEnabled(MoreStoneBlocks.class)) {
            basalt_stalactite = new BlockStalactite("basalt");
            marble_stalactite = new BlockStalactite("marble");
            limestone_stalactite = new BlockStalactite("limestone");
        }

        GameRegistry.registerWorldGenerator(new StalactiteGenerator(), 1000);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
