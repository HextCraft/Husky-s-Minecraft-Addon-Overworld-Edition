package team.hdt.neutronia.groups.dimensions.world.providers;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.groups.dimensions.features.SunBiomes;
import team.hdt.neutronia.groups.dimensions.features.SunDimension;
import team.hdt.neutronia.groups.dimensions.world.gen.sun.SunChunkGenerator;

public class SunWorldProvider extends WorldProvider {

    @Override
    protected void init() {
        super.init();
        biomeProvider = new BiomeProviderSingle(SunBiomes.SUN_MAIN);
    }

    @Override
    public DimensionType getDimensionType() {
        return SunDimension.SUN;
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
        return new Vec3d(0.909D, 0.788D, 0.121D);
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
        return new SunChunkGenerator(world);
    }

}