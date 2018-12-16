package team.hdt.neutronia_legacy.entity.render.layer;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.entity.EntityForsakenDiver;

@SideOnly(Side.CLIENT)
public class LayerForsakenDiverHelmet implements LayerRenderer<EntityForsakenDiver> {

    private static final ResourceLocation STRAY_CLOTHES_TEXTURES = new ResourceLocation("neutronia_legacy:textures/entity/sea/diver/forsaken_diver_helmet_overlay.png");
    private final RenderLivingBase<?> renderer;
    private final ModelZombie layerModel = new ModelZombie(0.25F, false);

    public LayerForsakenDiverHelmet(RenderLivingBase<?> p_i47183_1_) {
        this.renderer = p_i47183_1_;
    }

    public void doRenderLayer(EntityForsakenDiver entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.layerModel.setModelAttributes(this.renderer.getMainModel());
        this.layerModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.renderer.bindTexture(STRAY_CLOTHES_TEXTURES);
        this.layerModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public boolean shouldCombineTextures() {
        return false;
    }

}