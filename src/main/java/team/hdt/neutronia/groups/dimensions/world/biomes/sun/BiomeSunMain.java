package team.hdt.neutronia.groups.dimensions.world.biomes.sun;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeSunMain extends Biome {

    public BiomeSunMain() {
        super(new BiomeProperties("Sun").setBaseHeight(1.0F).setHeightVariation(0.1F).setTemperature(4.0F).setRainfall(0.0F).setRainDisabled());

        topBlock = Blocks.END_STONE.getDefaultState();
        fillerBlock = Blocks.END_STONE.getDefaultState();

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
    }

}
