package team.hdt.neutronia.groups.dimensions.world.biomes.moon;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeMoonMain extends Biome {

    public BiomeMoonMain() {
        super(new BiomeProperties("Moon").setBaseHeight(1.0F).setHeightVariation(0.1F).setTemperature(1.0F).setRainfall(0.0F).setRainDisabled());

        topBlock = Blocks.END_STONE.getDefaultState();
        fillerBlock = Blocks.END_STONE.getDefaultState();

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
    }

}
