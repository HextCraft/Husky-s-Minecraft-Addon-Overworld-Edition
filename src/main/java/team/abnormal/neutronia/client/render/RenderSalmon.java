package team.abnormal.neutronia.client.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.client.model.ModelSalmon;
import team.abnormal.neutronia.entity.passive.EntitySalmon;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderSalmon extends RenderLiving<EntitySalmon> {
    private static final ResourceLocation COD_LOCATION = new ResourceLocation(Reference.MOD_ID,"textures/entity/fish/salmon.png");

    public RenderSalmon(RenderManager p_i48864_1_) {
        super(p_i48864_1_, new ModelSalmon(), 0.25F);
    }

    @Nullable
    protected ResourceLocation getEntityTexture(EntitySalmon entity) {
        return COD_LOCATION;
    }

    protected void applyRotations(EntitySalmon entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
        if (!entityLiving.isInWater()) {
            GlStateManager.translate(0.1F, 0.1F, -0.1F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        }
    }
}