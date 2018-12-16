package team.hdt.neutronia_legacy.groups.dimensions.world.providers;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.groups.dimensions.features.AOBiomes;
import team.hdt.neutronia_legacy.groups.dimensions.features.AlienOverworld;
import team.hdt.neutronia_legacy.groups.dimensions.world.gen.mars.MarsChunkGenerator;

public class AOWorldProvider extends WorldProvider {

    @Override
    protected void init() {
        super.init();
        biomeProvider = new BiomeProviderSingle(AOBiomes.MARS_MAIN);
    }

    @Override
    public DimensionType getDimensionType() {
        return AlienOverworld.AO;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0.5F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return new Vec3d(0.909D, 0.215D, 0.125D);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1) {
        return 10.0F;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new MarsChunkGenerator(world);
    }

}