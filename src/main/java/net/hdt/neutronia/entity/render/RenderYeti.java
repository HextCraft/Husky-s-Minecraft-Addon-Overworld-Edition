package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityYeti;
import net.hdt.neutronia.entity.render.model.ModelYeti;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

public class RenderYeti extends RenderLiving<EntityYeti> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/yeti.png");

    public RenderYeti(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelYeti(), 0.5F);
    }

    protected ResourceLocation getEntityTexture(EntityYeti entity) {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityYeti entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(3.0f, 3.0f, 3.0f);
    }

    /*protected void applyRotations(EntityYeti entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

        if ((double) entityLiving.limbSwingAmount >= 0.01D) {
            float f = 13.0F;
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            GlStateManager.rotate(6.5F * f2, 0.0F, 0.0F, 1.0F);
        }
    }*/

}
