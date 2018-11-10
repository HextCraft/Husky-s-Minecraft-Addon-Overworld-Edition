package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.entity.EntityHost;
import net.hdt.neutronia.entity.render.model.ModelHost;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHost extends RenderLiving<EntityHost> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/host_inactive.png");

    public RenderHost(RenderManager manager) {
        super(manager, new ModelHost(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityHost entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(EntityHost entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }

}