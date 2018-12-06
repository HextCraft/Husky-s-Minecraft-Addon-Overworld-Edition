package team.hdt.neutronia.groups.dimensions.world.biomes.nether;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import team.hdt.neutronia.base.world.biome.BiomeNeutronia;

import static team.hdt.neutronia.base.lib.LibMisc.MOD_ID;

public class BiomeNether extends BiomeNeutronia {
    public BiomeNether(Biome.BiomeProperties properties, String name) {
        super(properties, MOD_ID, name);
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new NetherBiomeDecorator());
    }
}