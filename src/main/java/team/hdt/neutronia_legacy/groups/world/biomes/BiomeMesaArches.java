package team.hdt.neutronia_legacy.groups.world.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import team.hdt.neutronia_legacy.groups.building.features.MoreStoneBlocks;
import team.hdt.neutronia_legacy.properties.EnumNewStoneVariants;

public class BiomeMesaArches extends Biome {

    public BiomeMesaArches(BiomeProperties properties) {
        super((new BiomeProperties("Mesa Arches")).setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(2.0F).setRainfall(0.0F).setWaterColor(0x1b1b1b).setRainDisabled());
        topBlock = Blocks.SAND.getDefaultState();
        fillerBlock = MoreStoneBlocks.newStoneVariants[EnumNewStoneVariants.BASALT_COBBLE.getMetadata()].getDefaultState();
    }

}
