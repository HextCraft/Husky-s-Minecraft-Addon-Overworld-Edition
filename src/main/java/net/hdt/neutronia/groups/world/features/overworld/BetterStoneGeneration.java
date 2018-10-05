package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.base.handler.server.BiomeTypeConfigHandler;
import net.hdt.neutronia.base.handler.server.DimensionConfig;
import net.hdt.neutronia.groups.world.world.StoneInfoBasedGenerator;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class BetterStoneGeneration extends Component {

    private static StoneInfo graniteInfo, dioriteInfo, andesiteInfo;

    private static StoneInfo loadStoneInfo(String configCategory, String name, int clusterSize, int clusterRarity, int upperBound, int lowerBound, boolean enabled, String dims, Type... biomes) {
        String category = configCategory + "." + name;
        return new StoneInfo(category, clusterSize, clusterRarity, upperBound, lowerBound, enabled, dims, biomes);
    }

    @Override
    public void setupConfig() {
        int defSize = 14;
        int defRarity = 15;
        int defUpper = 80;
        int defLower = 20;

        graniteInfo = loadStoneInfo("granite", defSize, defRarity, defUpper, defLower, true, Type.MOUNTAIN, Type.HILLS);
        dioriteInfo = loadStoneInfo("diorite", defSize, defRarity, defUpper, defLower, true, Type.SANDY, Type.SAVANNA, Type.WASTELAND, Type.MUSHROOM);
        andesiteInfo = loadStoneInfo("andesite", defSize, defRarity, defUpper, defLower, true, Type.FOREST);
    }

    private StoneInfo loadStoneInfo(String name, int clusterSize, int clusterRarity, int upperBound, int lowerBound, boolean enabled, Type... biomes) {
        return loadStoneInfo(configCategory, name, clusterSize, clusterRarity, upperBound, lowerBound, enabled, "0", biomes);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        IBlockState graniteState = Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE);
        IBlockState dioriteState = Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE);
        IBlockState andesiteState = Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE);

        List<StoneInfoBasedGenerator> generators = new ArrayList<>();

        generators.add(new StoneInfoBasedGenerator(() -> graniteInfo, graniteState, "granite"));
        generators.add(new StoneInfoBasedGenerator(() -> dioriteInfo, dioriteState, "diorite"));
        generators.add(new StoneInfoBasedGenerator(() -> andesiteInfo, andesiteState, "andesite"));
    }

    @SubscribeEvent
    public void onOreGenerate(OreGenEvent.GenerateMinable event) {
        switch (event.getType()) {
            case GRANITE:
                if (graniteInfo.enabled)
                    event.setResult(Result.DENY);
                break;
            case DIORITE:
                if (dioriteInfo.enabled)
                    event.setResult(Result.DENY);
                break;
            case ANDESITE:
                if (andesiteInfo.enabled)
                    event.setResult(Result.DENY);
                break;
            default:
        }
    }

    @Override
    public boolean hasOreGenSubscriptions() {
        return true;
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    public static class StoneInfo {

        public final boolean enabled;
        public final int clusterSize, clusterRarity, upperBound, lowerBound;
        public final boolean clustersRarityPerChunk;

        public final DimensionConfig dims;
        public final List<Type> allowedBiomes;

        private StoneInfo(String category, int clusterSize, int clusterRarity, int upperBound, int lowerBound, boolean enabled, String dimStr, Type... biomes) {
            this.enabled = GroupLoader.config.getBoolean("Enabled", category, true, "") && enabled;
            this.clusterSize = GroupLoader.config.getInt("Cluster Radius", category, clusterSize, 0, Integer.MAX_VALUE, "");
            this.clusterRarity = GroupLoader.config.getInt("Cluster Rarity", category, clusterRarity, 0, Integer.MAX_VALUE, "Out of how many chunks would one of these clusters generate");
            this.upperBound = GroupLoader.config.getInt("Y Level Max", category, upperBound, 0, 255, "");
            this.lowerBound = GroupLoader.config.getInt("Y Level Min", category, lowerBound, 0, 255, "");
            clustersRarityPerChunk = GroupLoader.config.getBoolean("Invert Cluster Rarity", category, false, "Setting this to true will make the 'Cluster Rarity' feature be X per chunk rather than 1 per X chunks");

            dims = new DimensionConfig(category, dimStr);
            allowedBiomes = BiomeTypeConfigHandler.parseBiomeTypeArrayConfig("Allowed Biome Types", category, biomes);
        }
    }

}

