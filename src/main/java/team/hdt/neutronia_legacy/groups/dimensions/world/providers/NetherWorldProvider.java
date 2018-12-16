package team.hdt.neutronia_legacy.groups.dimensions.world.providers;

import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.groups.dimensions.features.RevampedNether;
import team.hdt.neutronia_legacy.groups.dimensions.world.biomes.nether.NetherBiomeProvider;
import team.hdt.neutronia_legacy.groups.dimensions.world.gen.nether.NetherChunkGenerator;

public class NetherWorldProvider extends WorldProviderHell {
    @Override
    public void init() {
        super.init();
        biomeProvider = new NetherBiomeProvider(world);
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new NetherChunkGenerator(world);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int chunkX, int chunkZ) {
        return !RevampedNether.disableNetherFog;
    }

}