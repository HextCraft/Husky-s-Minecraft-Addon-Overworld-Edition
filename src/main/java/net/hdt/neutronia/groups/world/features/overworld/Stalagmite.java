package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.base.handler.server.DimensionConfig;
import net.hdt.neutronia.groups.building.features.MoreStoneBlocks;
import net.hdt.neutronia.groups.world.blocks.BlockStalagmite;
import net.hdt.neutronia.groups.world.world.StalagmiteGenerator;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Stalagmite extends Component {

    public static Block stone_stalagmite, granite_stalagmite, diorite_stalagmite,
            andesite_stalagmite, basalt_stalagmite, marble_stalagmite, limestone_stalagmite,
            netherrack_stalagmite, ice_stalagmite, packed_ice_stalagmite;

    public static int tries, clusterCount, netherTries, netherClusterCount, maxHeight;
    public static DimensionConfig dimensionConfig;

    @Override
    public void setupConfig() {
        tries = loadProperty("Cluster Attempts Per Chunk", 60).get();
        clusterCount = loadProperty("Stalagmite Per Cluster", 12).get();
        netherTries = loadProperty("Cluster Attempts Per Chunk (Nether)", 4).get();
        netherClusterCount = loadProperty("Stalagmite Per Cluster (Nether)", 12).get();
        maxHeight = loadProperty("Highest Y Level", 55).get();

        dimensionConfig = new DimensionConfig(getCategory(), "0,-1");
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        stone_stalagmite = new BlockStalagmite("stone");
        granite_stalagmite = new BlockStalagmite("granite");
        diorite_stalagmite = new BlockStalagmite("diorite");
        andesite_stalagmite = new BlockStalagmite("andesite");
        netherrack_stalagmite = new BlockStalagmite("netherrack").setNetherrack();
        ice_stalagmite = new BlockStalagmite("ice");
        packed_ice_stalagmite = new BlockStalagmite("packed_ice");

        if(GroupLoader.isComponentEnabled(MoreStoneBlocks.class)) {
            basalt_stalagmite = new BlockStalagmite("basalt");
            marble_stalagmite = new BlockStalagmite("marble");
            limestone_stalagmite = new BlockStalagmite("limestone");
        }

        GameRegistry.registerWorldGenerator(new StalagmiteGenerator(), 1000);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
