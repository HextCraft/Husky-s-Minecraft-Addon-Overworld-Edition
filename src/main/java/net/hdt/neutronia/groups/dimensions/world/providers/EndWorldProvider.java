package net.hdt.neutronia.groups.dimensions.world.providers;

import net.hdt.neutronia.groups.dimensions.world.biomes.end.EndBiomeProvider;
import net.hdt.neutronia.groups.dimensions.world.gen.end.EndChunkGenerator;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.gen.IChunkGenerator;

public class EndWorldProvider extends WorldProviderEnd {
    @Override
    public void init() {
        super.init();
        biomeProvider = new EndBiomeProvider(world);
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new EndChunkGenerator(world, getSpawnCoordinate());
    }
}