package team.hdt.neutronia.base.world.biome;

import net.minecraft.world.biome.Biome;

public class BiomeNeutronia extends Biome {
    public BiomeNeutronia(BiomeProperties properties, String modId, String name) {
        super(properties);
        setRegistryName(modId + ":" + name);
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();
    }
}