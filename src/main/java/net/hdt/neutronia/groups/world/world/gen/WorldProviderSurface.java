package net.hdt.neutronia.groups.world.world.gen;

import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderSurface extends net.minecraft.world.WorldProviderSurface {

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorOverworld(this.world, this.world.getSeed(), this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getWorldInfo().getGeneratorOptions());
    }

}