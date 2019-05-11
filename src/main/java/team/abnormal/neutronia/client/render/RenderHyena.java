package team.abnormal.neutronia.client.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.client.model.ModelHyena;
import team.abnormal.neutronia.entity.passive.EntityHyena;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderHyena extends RenderLiving<EntityHyena> {
    private static final ResourceLocation HYENA_TEXTURE = new ResourceLocation(Reference.MOD_ID,"textures/entity/hyena/hyena.png");
    private static final ResourceLocation HYENA_ANGRY_TEXTURE = new ResourceLocation(Reference.MOD_ID,"textures/entity/hyena/hyena_angry.png");
    private static final ResourceLocation HYENA_BAGU_TEXTURE = new ResourceLocation(Reference.MOD_ID,"textures/entity/hyena/hyena_bagu.png");

    public RenderHyena(RenderManager p_i48864_1_) {
        super(p_i48864_1_, new ModelHyena(), 0.5F);
    }

    @Override
    public void doRender(EntityHyena entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        if (entity.isWolfWet())
        {
            float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
            GlStateManager.color(f, f, f);
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected float handleRotationFloat(EntityHyena livingBase, float partialTicks) {
        return livingBase.getTailRotation();
    }

    @Nullable
    protected ResourceLocation getEntityTexture(EntityHyena entity) {
        String s = TextFormatting.getTextWithoutFormattingCodes(entity.getName());

        if(s != null && "bagu_chan".equals(s)){
            return HYENA_BAGU_TEXTURE;
        }else if(entity.isAngry()){
            return HYENA_ANGRY_TEXTURE;
        }
        else {
            return HYENA_TEXTURE;
        }
    }

}