package net.hdt.neutronia.groups.dimensions.world.biomes.end;

import net.hdt.neutronia.base.world.biome.BiomeNeutronia;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;

public class BiomeEnd extends BiomeNeutronia {
    public BiomeEnd(Biome.BiomeProperties properties, String name) {
        super(properties, MOD_ID, name);
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new EndBiomeDecorator());
    }
}