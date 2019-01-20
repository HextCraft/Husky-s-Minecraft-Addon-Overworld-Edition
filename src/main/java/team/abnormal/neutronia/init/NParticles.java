package team.abnormal.neutronia.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public enum NParticles implements IStringSerializable {

    CAMPFIRE_SIGNAL_SMOKE("campfire_signal_smoke", 49),
    CAMPFIRE_COSY_SMOKE("campfire_cosy_smoke", 50);

    private String name;
    private int ID;

    NParticles(String name, int ID) {
        this.name = name;
        this.ID = ID;
        ParticleManager manager = new ParticleManager(Minecraft.getMinecraft().world, Minecraft.getMinecraft().getTextureManager());
        manager.registerParticle(ID, new IParticleFactory() {
            @Nullable
            @Override
            public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
                return null;
            }
        });
    }

    @Override
    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

}