package team.abnormals.neutronia.world.biomes;

import net.minecraft.world.biome.BiomeForest;

public class AutumnalForestBiome extends BiomeForest {

    public AutumnalForestBiome() {
        super(Type.NORMAL, new BiomeProperties("Autumnal Forest").setTemperature(0.7F).setRainfall(0.8F));
    }

}
