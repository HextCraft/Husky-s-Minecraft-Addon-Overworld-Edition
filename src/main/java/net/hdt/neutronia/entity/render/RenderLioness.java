package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.entity.EntityLioness;
import net.hdt.neutronia.entity.render.model.ModelLioness;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLioness extends RenderLiving<EntityLioness> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/lioness.png");

    public RenderLioness(RenderManager manager) {
        super(manager, new ModelLioness(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLioness entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(EntityLioness entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
