package team.hdt.neutronia.init;

import net.minecraft.world.biome.Biome;
import team.hdt.neutronia.world.biomes.BiomeBambooForest;

public class NBiomes {

    public static Biome BAMBOO_FOREST;

    public static void init() {
        BAMBOO_FOREST = new BiomeBambooForest();
    }

}
