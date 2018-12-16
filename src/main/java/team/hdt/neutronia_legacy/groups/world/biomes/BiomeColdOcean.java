package team.hdt.neutronia_legacy.groups.world.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeColdOcean extends Biome {
    public BiomeColdOcean(Biome.BiomeProperties properties) {
        super(properties);
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.GRAVEL.getDefaultState();
    }

    public Biome.TempCategory getTempCategory() {
        return TempCategory.COLD;
    }
}