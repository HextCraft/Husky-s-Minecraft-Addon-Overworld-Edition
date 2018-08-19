package net.hdt.neutronia.base.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderSkyboxCube {
    private final ResourceLocation[] field_209143_a = new ResourceLocation[6];

    public RenderSkyboxCube(ResourceLocation p_i49378_1_) {
        for (int lvt_2_1_ = 0; lvt_2_1_ < 6; ++lvt_2_1_) {
            this.field_209143_a[lvt_2_1_] = new ResourceLocation(p_i49378_1_.getNamespace(), p_i49378_1_.getPath() + '_' + lvt_2_1_ + ".png");
        }

    }

    public void func_209142_a(Minecraft p_209142_1_, float p_209142_2_, float p_209142_3_) {
        Tessellator lvt_4_1_ = Tessellator.getInstance();
        BufferBuilder lvt_5_1_ = lvt_4_1_.getBuffer();
        GlStateManager.matrixMode(5889);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
//        GlStateManager.ortho(85.0D, (double)p_209142_1_.displayWidth / (double)p_209142_1_.displayHeight, 0.05F, 10.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);

        for (int lvt_7_1_ = 0; lvt_7_1_ < 4; ++lvt_7_1_) {
            GlStateManager.pushMatrix();
            float lvt_8_1_ = ((float) (lvt_7_1_ % 2) / 2.0F - 0.5F) / 256.0F;
            float lvt_9_1_ = ((float) (lvt_7_1_ / 2) / 2.0F - 0.5F) / 256.0F;
            float lvt_10_1_ = 0.0F;
            GlStateManager.translate(lvt_8_1_, lvt_9_1_, 0.0F);
            GlStateManager.rotate(p_209142_2_, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(p_209142_3_, 0.0F, 1.0F, 0.0F);

            for (int lvt_11_1_ = 0; lvt_11_1_ < 6; ++lvt_11_1_) {
                p_209142_1_.getTextureManager().bindTexture(this.field_209143_a[lvt_11_1_]);
                lvt_5_1_.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                int lvt_12_1_ = 255 / (lvt_7_1_ + 1);
                if (lvt_11_1_ == 0) {
                    lvt_5_1_.pos(-1.0D, -1.0D, 1.0D).tex(0.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(-1.0D, 1.0D, 1.0D).tex(0.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(1.0D, 1.0D, 1.0D).tex(1.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(1.0D, -1.0D, 1.0D).tex(1.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                }

                if (lvt_11_1_ == 1) {
                    lvt_5_1_.pos(1.0D, -1.0D, 1.0D).tex(0.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(1.0D, 1.0D, 1.0D).tex(0.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(1.0D, 1.0D, -1.0D).tex(1.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(1.0D, -1.0D, -1.0D).tex(1.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                }

                if (lvt_11_1_ == 2) {
                    lvt_5_1_.pos(1.0D, -1.0D, -1.0D).tex(0.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(1.0D, 1.0D, -1.0D).tex(0.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(-1.0D, 1.0D, -1.0D).tex(1.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(-1.0D, -1.0D, -1.0D).tex(1.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                }

                if (lvt_11_1_ == 3) {
                    lvt_5_1_.pos(-1.0D, -1.0D, -1.0D).tex(0.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(-1.0D, 1.0D, -1.0D).tex(0.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(-1.0D, 1.0D, 1.0D).tex(1.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(-1.0D, -1.0D, 1.0D).tex(1.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                }

                if (lvt_11_1_ == 4) {
                    lvt_5_1_.pos(-1.0D, -1.0D, -1.0D).tex(0.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(-1.0D, -1.0D, 1.0D).tex(0.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(1.0D, -1.0D, 1.0D).tex(1.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(1.0D, -1.0D, -1.0D).tex(1.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                }

                if (lvt_11_1_ == 5) {
                    lvt_5_1_.pos(-1.0D, 1.0D, 1.0D).tex(0.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(-1.0D, 1.0D, -1.0D).tex(0.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(1.0D, 1.0D, -1.0D).tex(1.0D, 1.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                    lvt_5_1_.pos(1.0D, 1.0D, 1.0D).tex(1.0D, 0.0D).color(255, 255, 255, lvt_12_1_).endVertex();
                }

                lvt_4_1_.draw();
            }

            GlStateManager.popMatrix();
            GlStateManager.colorMask(true, true, true, false);
        }

        lvt_5_1_.setTranslation(0.0D, 0.0D, 0.0D);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(5889);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
        GlStateManager.enableDepth();
    }
}
