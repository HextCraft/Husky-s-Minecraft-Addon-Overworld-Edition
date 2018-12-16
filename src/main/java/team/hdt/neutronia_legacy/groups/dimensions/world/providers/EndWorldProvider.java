package team.hdt.neutronia_legacy.groups.dimensions.world.providers;

import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.gen.IChunkGenerator;
import team.hdt.neutronia_legacy.groups.dimensions.world.biomes.end.EndBiomeProvider;
import team.hdt.neutronia_legacy.groups.dimensions.world.gen.end.EndChunkGenerator;

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