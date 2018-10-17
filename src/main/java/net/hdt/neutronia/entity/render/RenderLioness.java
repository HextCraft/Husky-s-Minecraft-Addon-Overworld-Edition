package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.entity.EntitySeaTurtle;
import net.hdt.neutronia.entity.render.model.ModelSeaTurtle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLioness extends RenderLiving<EntitySeaTurtle> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/sea/ocean_creatures/sea_turtle.png");

    public RenderLioness(RenderManager manager) {
        super(manager, new ModelSeaTurtle(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySeaTurtle entity) {
        return SCORP_TEXTURE;
    }

    @Override
    protected void applyRotations(EntitySeaTurtle entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
