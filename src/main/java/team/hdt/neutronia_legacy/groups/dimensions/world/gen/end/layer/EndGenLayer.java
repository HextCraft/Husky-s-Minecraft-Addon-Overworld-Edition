package team.hdt.neutronia_legacy.groups.dimensions.world.gen.end.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;

public abstract class EndGenLayer extends GenLayer {
    public EndGenLayer(long seed) {
        super(seed);
    }

    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType type) {
        int biomeSize = type == WorldType.LARGE_BIOMES ? 8 : 6;
        biomeSize = getModdedBiomeSize(type, biomeSize);

        GenLayer genLayer = new EndBiomeGenLayer(1L);
        genLayer = new GenLayerFuzzyZoom(1000L, genLayer);
        genLayer = GenLayerZoom.magnify(1000L, genLayer, biomeSize);
        genLayer = new GenLayerSmooth(1000L, genLayer);
        GenLayer genLayerVoronoiZoom = new GenLayerVoronoiZoom(10L, genLayer);

        genLayer.initWorldGenSeed(seed);
        genLayerVoronoiZoom.initWorldGenSeed(seed);

        return new GenLayer[]{genLayer, genLayerVoronoiZoom};
    }
}