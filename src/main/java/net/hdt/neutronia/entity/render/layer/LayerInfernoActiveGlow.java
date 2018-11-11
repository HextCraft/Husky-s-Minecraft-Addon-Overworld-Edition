package net.hdt.neutronia.entity.render.layer;

import net.hdt.neutronia.entity.EntityInferno;
import net.hdt.neutronia.entity.render.RenderInferno;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerInfernoActiveGlow implements LayerRenderer<EntityInferno> {

    private static final ResourceLocation GLOWING_TEXTURE = new ResourceLocation("neutronia:textures/entity/inferno_active_glow.png");
    private final RenderInferno renderer;

    public LayerInfernoActiveGlow(RenderInferno renderer) {
        this.renderer = renderer;
    }

    public void doRenderLayer(EntityInferno entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!entitylivingbaseIn.isPassive()) {
            this.renderer.bindTexture(GLOWING_TEXTURE);
            GlStateManager.enableBlend();
//            GlStateManager.disableAlpha();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
            int i = 61680;
            int j = 61680;
            int k = 0;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
            GlStateManager.enableLighting();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
            this.renderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
            this.renderer.setLightmap(entitylivingbaseIn);
            GlStateManager.depthMask(true);
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }

}