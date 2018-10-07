package net.hdt.neutronia.groups.world.biomes;

import net.hdt.neutronia.groups.building.features.MoreStoneBlocks;
import net.hdt.neutronia.properties.EnumNewStoneVariants;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeMesaArches extends Biome {

    public BiomeMesaArches(BiomeProperties properties) {
        super((new BiomeProperties("Mesa Arches")).setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(2.0F).setRainfall(0.0F).setWaterColor(0x1b1b1b).setRainDisabled());
        topBlock = Blocks.SAND.getDefaultState();
        fillerBlock = MoreStoneBlocks.newStoneVariants[EnumNewStoneVariants.BASALT_COBBLE.getMetadata()].getDefaultState();
    }

}
