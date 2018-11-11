package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.entity.neutral.EntityPanda;
import net.hdt.neutronia.entity.render.model.ModelPanda;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPanda extends RenderLiving<EntityPanda> {
    private static final ResourceLocation GRIZZLY_BEAR_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/entity/bears/panda/panda.png");

    public RenderPanda(RenderManager manager) {
        super(manager, new ModelPanda(), 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPanda entity) {
        return GRIZZLY_BEAR_TEXTURE;
    }

    @Override
    public void doRender(EntityPanda entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPanda entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

}