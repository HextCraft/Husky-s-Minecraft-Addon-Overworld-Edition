package team.hdt.neutronia.groups.world.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeLukewarmOcean extends Biome {
    public BiomeLukewarmOcean(Biome.BiomeProperties properties) {
        super(properties);
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.GRAVEL.getDefaultState();
    }

    public Biome.TempCategory getTempCategory() {
        return TempCategory.MEDIUM;
    }
}