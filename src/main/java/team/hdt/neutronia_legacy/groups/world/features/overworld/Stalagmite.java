package team.hdt.neutronia_legacy.groups.world.features.overworld;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.base.groups.GroupLoader;
import team.hdt.neutronia_legacy.base.handler.server.DimensionConfig;
import team.hdt.neutronia_legacy.groups.building.features.MoreStoneBlocks;
import team.hdt.neutronia_legacy.groups.world.blocks.BlockStalagmite;
import team.hdt.neutronia_legacy.groups.world.world.StalagmiteGenerator;

public class Stalagmite extends Component {

    public static Block stone_stalagmite, granite_stalagmite, diorite_stalagmite,
            andesite_stalagmite, basalt_stalagmite, marble_stalagmite, limestone_stalagmite,
            netherrack_stalagmite, ice_stalagmite, packed_ice_stalagmite, crystal_stalagmite,
            end_stalagmite, clay_stalagmite, dirt_stalagmite, sandstone_stalagmite;

    public static int tries, clusterCount, netherTries, netherClusterCount, maxHeight;
    public static DimensionConfig dimensionConfig;

    @Override
    public void setupConfig() {
        tries = loadPropInt("Cluster Attempts Per Chunk", "", 60);
        clusterCount = loadPropInt("Stalagmite Per Cluster", "", 12);
        netherTries = loadPropInt("Cluster Attempts Per Chunk (Nether)", "", 4);
        netherClusterCount = loadPropInt("Stalagmite Per Cluster (Nether)", "", 12);
        maxHeight = loadPropInt("Highest Y Level", "", 55);

        dimensionConfig = new DimensionConfig(configCategory, "0,-1");
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        stone_stalagmite = new BlockStalagmite("stone", false);
        granite_stalagmite = new BlockStalagmite("granite", false);
        diorite_stalagmite = new BlockStalagmite("diorite", false);
        andesite_stalagmite = new BlockStalagmite("andesite", false);
        netherrack_stalagmite = new BlockStalagmite("netherrack", false).setNetherrack();
        ice_stalagmite = new BlockStalagmite("ice", false);
        packed_ice_stalagmite = new BlockStalagmite("packed_ice", false);
        crystal_stalagmite = new BlockStalagmite("crystal", true);
        end_stalagmite = new BlockStalagmite("end_stone", false);
        clay_stalagmite = new BlockStalagmite("clay", false);
        dirt_stalagmite = new BlockStalagmite("dirt", false);
        sandstone_stalagmite = new BlockStalagmite("sandstone", false);

        if (GroupLoader.isFeatureEnabled(MoreStoneBlocks.class)) {
            basalt_stalagmite = new BlockStalagmite("basalt", false);
            marble_stalagmite = new BlockStalagmite("marble", false);
            limestone_stalagmite = new BlockStalagmite("limestone", false);
        }

        GameRegistry.registerWorldGenerator(new StalagmiteGenerator(), 1000);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
