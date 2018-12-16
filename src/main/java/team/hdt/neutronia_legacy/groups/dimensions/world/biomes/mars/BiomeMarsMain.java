package team.hdt.neutronia_legacy.groups.dimensions.world.biomes.mars;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeMarsMain extends Biome {

    public BiomeMarsMain() {
        super(new BiomeProperties("Mars").setBaseHeight(1.0F).setHeightVariation(0.1F).setTemperature(1.0F).setRainfall(0.0F).setRainDisabled());

        topBlock = Blocks.END_STONE.getDefaultState();
        fillerBlock = Blocks.END_STONE.getDefaultState();

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
    }

}
