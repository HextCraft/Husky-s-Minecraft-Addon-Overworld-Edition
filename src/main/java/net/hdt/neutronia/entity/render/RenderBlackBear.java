package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.entity.neutral.EntityBlackBear;
import net.minecraft.client.model.ModelPolarBear;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlackBear extends RenderLiving<EntityBlackBear> {
    private static final ResourceLocation GRIZZLY_BEAR_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/entity/bears/black_bear.png");

    public RenderBlackBear(RenderManager manager) {
        super(manager, new ModelPolarBear(), 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBlackBear entity) {
        return GRIZZLY_BEAR_TEXTURE;
    }

    @Override
    public void doRender(EntityBlackBear entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityBlackBear entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

}