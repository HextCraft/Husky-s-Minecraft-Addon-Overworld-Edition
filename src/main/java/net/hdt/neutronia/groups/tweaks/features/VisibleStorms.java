package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Random;

public class VisibleStorms extends Component {
    private static boolean DUST_STORMS;
    private static boolean SAND_STORMS;
    private static int AIR_PARTICLES;
    private float currentRed, currentGreen, currentBlue;
    private float currentDistance, currentDistanceScale;
    private float desiredRed, desiredGreen, desiredBlue;
    private float desiredDistance, desiredDistanceScale;

    @SideOnly(Side.CLIENT)
    private static void renderFog(int fogMode, float farPlaneDistance, float farPlaneDistanceScale) {
        if (fogMode < 0) {
            GlStateManager.setFogStart(0.0F);
            GlStateManager.setFogEnd(farPlaneDistance);
        } else {
            GlStateManager.setFogEnd(farPlaneDistance);
            GlStateManager.setFogStart(farPlaneDistance * farPlaneDistanceScale);
        }
    }

    @Override
    public void setupConfig() {
        DUST_STORMS = loadPropBool("Dust Storms", "Storms are clearly visible in dry biomes.", true);
        SAND_STORMS = loadPropBool("Sand Storms", "Adds a fog change during storms in deserts.", true);
        AIR_PARTICLES = loadPropInt("Air Particle Count", "How many air particles should be created, too many may contribute to lag.", 9);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent tickEvent) {
        EntityPlayer entity = tickEvent.player;
        if (entity == null)
            return;
        World world = entity.world;
        if (world == null || !world.isRemote)
            return;
        if (DUST_STORMS) {
            ParticleManager particleManager = Minecraft.getMinecraft().effectRenderer;

            Random random = world.rand;
            BlockPos pos = entity.getPosition();
            int radius = 32; //blocks
            for (int i = 0; i < AIR_PARTICLES; i++) {
                BlockPos posAir = pos.add(random.nextInt(radius * 2 + 1) - radius, random.nextInt(radius * 2 + 1) - radius, random.nextInt(radius * 2 + 1) - radius);
                if (world.canSeeSky(posAir) && shouldStorm(world, posAir)) {
                    Particle particleAir = particleManager.spawnEffectParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleID(), posAir.getX() + random.nextDouble(), posAir.getY() + random.nextDouble(), posAir.getZ() + random.nextDouble(), -0.5 - random.nextDouble() * 0.6, 0.0, 0.0);
                    if (particleAir != null)
                        particleAir.setRBGColorF(1.0f, 1.0f, 1.0f);
                }
            }
        }

        if (SAND_STORMS) {
            float epsilon = 0.001f;
            if (Math.abs(currentDistance - desiredDistance) > epsilon)
                currentDistance += (desiredDistance - currentDistance) * 0.2; //TODO: We can do better.
            if (Math.abs(currentDistanceScale - desiredDistanceScale) > epsilon)
                currentDistanceScale += (desiredDistanceScale - currentDistanceScale) * 0.2; //TODO: We can do better.
            if (Math.abs(currentRed - desiredRed) > epsilon)
                currentRed += (desiredRed - currentRed) * 0.2;
            if (Math.abs(currentGreen - desiredGreen) > epsilon)
                currentGreen += (desiredGreen - currentGreen) * 0.2;
            if (Math.abs(currentBlue - desiredBlue) > epsilon)
                currentBlue += (desiredBlue - currentBlue) * 0.2;
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void fogDistance(EntityViewRenderEvent.RenderFogEvent fogEvent) {
        if (!SAND_STORMS)
            return;
        Entity entity = fogEvent.getEntity();
        World world = entity.world;
        BlockPos pos = entity.getPosition();

        if (world.isRaining()) {
            desiredDistance = 0;
            desiredDistanceScale = 0;
            int totalweight = 0;
            BlockPos[] probes = new BlockPos[]{pos, pos.add(1, 0, 0), pos.add(0, 0, 1), pos.add(-1, 0, 0), pos.add(0, 0, -1)};
            for (BlockPos probepos : probes) {
                boolean aboveground = world.canSeeSky(probepos);
                if (world.getBiome(probepos) == Biomes.DESERT || world.getBiome(probepos) == Biomes.DESERT_HILLS || world.getBiome(probepos) == Biomes.MUTATED_DESERT && aboveground) {
                    desiredDistance += fogEvent.getFarPlaneDistance() / 3f;
                    desiredDistanceScale += -1.0f;
                    totalweight += 1;
                } else if (aboveground) {
                    desiredDistance += fogEvent.getFarPlaneDistance();
                    desiredDistanceScale += 0.25f;
                    totalweight += 1;
                }
            }
            desiredDistance /= totalweight;
            desiredDistanceScale /= totalweight;
        } else {
            desiredDistance = fogEvent.getFarPlaneDistance();
            desiredDistanceScale = 0.25F;
        }

        if (Math.abs(fogEvent.getFarPlaneDistance() - currentDistance) > 0.001f)
            renderFog(fogEvent.getFogMode(), currentDistance, currentDistanceScale);

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void fogColor(EntityViewRenderEvent.FogColors fogEvent) {
        if (!SAND_STORMS)
            return;
        Entity entity = fogEvent.getEntity();
        World world = entity.world;
        BlockPos pos = entity.getPosition();
        Color desiredcolor = new Color(
                Math.min(fogEvent.getRed(), 1.0f),
                Math.min(fogEvent.getGreen(), 1.0f),
                Math.min(fogEvent.getBlue(), 1.0f)
        );
        if (world.isRaining()) {
            float red = 0;
            float green = 0;
            float blue = 0;
            int totalweight = 0;
            BlockPos[] probes = new BlockPos[]{pos, pos.add(1, 0, 0), pos.add(0, 0, 1), pos.add(-1, 0, 0), pos.add(0, 0, -1)};
            for (BlockPos probepos : probes) {
                boolean aboveground = world.canSeeSky(probepos);
                if (world.getBiome(probepos) == Biomes.DESERT || world.getBiome(probepos) == Biomes.DESERT_HILLS || world.getBiome(probepos) == Biomes.MUTATED_DESERT) {
                    Biome biome = world.getBiome(probepos);
                    MapColor mapcolor = biome.topBlock.getMapColor(world, probepos);
                    Color color = new Color(mapcolor.colorValue);
                    red += 2 * (color.getRed() / 255.0f);
                    green += 2 * (color.getGreen() / 255.0f);
                    blue += 2 * (color.getBlue() / 255.0f);
                    totalweight += 2;
                } else if (aboveground) {
                    red += fogEvent.getRed();
                    green += fogEvent.getGreen();
                    blue += fogEvent.getBlue();
                    totalweight += 1;
                }
            }
            desiredcolor = new Color(Math.min(red / totalweight, 1.0f), Math.min(green / totalweight, 1.0f), Math.min(blue / totalweight, 1.0f));
            fogEvent.setRed(currentRed / 255.0f);
            fogEvent.setGreen(currentGreen / 255.0f);
            fogEvent.setBlue(currentBlue / 255.0f);
        }

        desiredRed = desiredcolor.getRed();
        desiredGreen = desiredcolor.getGreen();
        desiredBlue = desiredcolor.getBlue();
    }

    private boolean shouldStorm(World world, BlockPos pos) {
        Biome biome = world.getBiome(pos);
        return world.isRaining() && !biome.canRain() && !biome.isSnowyBiome();
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public String[] getIncompatibleMods() {
        return new String[]{
                ""
        };
    }

}