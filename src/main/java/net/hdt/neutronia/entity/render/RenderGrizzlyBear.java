package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.entity.neutral.EntityGrizzlyBear;
import net.minecraft.client.model.ModelPolarBear;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGrizzlyBear extends RenderLiving<EntityGrizzlyBear>
{
    private static final ResourceLocation GRIZZLY_BEAR_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/entity/bears/grizzly_bear.png");

    public RenderGrizzlyBear(RenderManager manager)
    {
        super(manager, new ModelPolarBear(), 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGrizzlyBear entity)
    {
        return GRIZZLY_BEAR_TEXTURE;
    }

    @Override
    public void doRender(EntityGrizzlyBear entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityGrizzlyBear entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

}