package team.hdt.neutronia.groups.dimensions.world.gen.nether.layer;

import net.minecraft.init.Biomes;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import team.hdt.neutronia.groups.dimensions.world.biomes.nether.NNBiomeManager;

import java.util.List;

public class NetherBiomeGenLayer extends NetherGenLayer {
    public NetherBiomeGenLayer(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaZ, int areaWidth, int areaHeight) {
        int[] outputs = IntCache.getIntCache(areaWidth * areaHeight);

        for (int z = 0; z < areaHeight; z++) {
            for (int x = 0; x < areaWidth; x++) {
                initChunkSeed(x + areaX, z + areaZ);
                outputs[x + z * areaWidth] = Biome.getIdForBiome(getRandomBiome());
            }
        }

        return outputs;
    }

    private Biome getRandomBiome() {
        List<BiomeManager.BiomeEntry> biomeEntryList = NNBiomeManager.getBiomes();
        int biomeWeights = WeightedRandom.getTotalWeight(biomeEntryList);
        return biomeWeights <= 0 ? Biomes.HELL : WeightedRandom.getRandomItem(biomeEntryList, nextInt(biomeWeights)).biome;
    }
}