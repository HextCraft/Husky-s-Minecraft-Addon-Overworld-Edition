package team.hdt.neutronia.groups.world.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeWarmOcean extends Biome {
    public BiomeWarmOcean(Biome.BiomeProperties properties) {
        super(properties);
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.SAND.getDefaultState();
    }

    @Override
    public int getWaterColorMultiplier() {
        return 0xFFFFFF;
    }

    public Biome.TempCategory getTempCategory() {
        return TempCategory.WARM;
    }

}