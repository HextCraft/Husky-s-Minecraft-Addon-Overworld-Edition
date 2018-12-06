package team.hdt.neutronia.groups.dimensions.world.gen.end.layer;

import net.minecraft.init.Biomes;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import team.hdt.neutronia.groups.dimensions.world.biomes.end.NEBiomeManager;

import java.util.List;

public class EndBiomeGenLayer extends EndGenLayer {
    public EndBiomeGenLayer(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaZ, int areaWidth, int areaHeight) {
        int[] outputs = IntCache.getIntCache(areaWidth * areaHeight);

        for (int z = 0; z < areaHeight; z++) {
            for (int x = 0; x < areaWidth; x++) {
                int chunkX = areaX + x;
                int chunkZ = areaZ + z;

                initChunkSeed(chunkX, chunkZ);

                Biome biome = null;

                int area = (chunkX * chunkX) + (chunkZ * chunkZ);

                if (area < 2) {
                    biome = getRandomBiome(NEBiomeManager.BiomeType.HEART);
                }
                if (area >= 2 && area < 762) {
                    biome = getRandomBiome(NEBiomeManager.BiomeType.ORBIT);
                }
                if (area >= 762 && area < 7630000) {
                    biome = getRandomBiome(NEBiomeManager.BiomeType.DIVIDE);
                } else if (area >= 7630000) {
                    biome = getRandomBiome(NEBiomeManager.BiomeType.EXPANSE);
                }

                outputs[x + z * areaWidth] = Biome.getIdForBiome(biome);
            }
        }

        return outputs;
    }

    private Biome getRandomBiome(NEBiomeManager.BiomeType type) {
        List<BiomeManager.BiomeEntry> biomeEntryList = NEBiomeManager.getBiomes(type);
        int biomeWeights = WeightedRandom.getTotalWeight(biomeEntryList);
        return biomeWeights <= 0 ? Biomes.SKY : WeightedRandom.getRandomItem(biomeEntryList, nextInt(biomeWeights)).biome;
    }
}
