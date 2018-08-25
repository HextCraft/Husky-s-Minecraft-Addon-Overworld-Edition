package net.hdt.neutronia.base.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;

public class SkyboxRender {
    private final Minecraft field_209145_a;
    private final SkyboxCubeRender field_209146_b;
    private float field_209147_c;
    private double guiScaleFactor = (double) this.getScaleFactor(Minecraft.getMinecraft().gameSettings.guiScale);

    public SkyboxRender(SkyboxCubeRender p_i49377_1_) {
        this.field_209146_b = p_i49377_1_;
        this.field_209145_a = Minecraft.getMinecraft();
    }

    public void func_209144_a(float p_209144_1_) {
        this.field_209147_c += p_209144_1_;
        this.field_209146_b.func_209142_a(this.field_209145_a, MathHelper.sin(this.field_209147_c * 0.001F) * 5.0F + 25.0F, -this.field_209147_c * 0.1F);
        GlStateManager.clear(256);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, (double) Minecraft.getMinecraft().displayWidth / getGuiScaleFactor(), (double) Minecraft.getMinecraft().displayHeight / getGuiScaleFactor(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
    }

    double getGuiScaleFactor() {
        return guiScaleFactor;
    }

    public int getScaleFactor(int p_getScaleFactor_1_) {
        int lvt_2_1_;
        for (lvt_2_1_ = 1; lvt_2_1_ != p_getScaleFactor_1_ && lvt_2_1_ < Minecraft.getMinecraft().displayWidth && lvt_2_1_ < Minecraft.getMinecraft().displayHeight && Minecraft.getMinecraft().displayWidth / (lvt_2_1_ + 1) >= 320 && Minecraft.getMinecraft().displayHeight / (lvt_2_1_ + 1) >= 240; ++lvt_2_1_) {
            ;
        }

        if (Minecraft.getMinecraft().isUnicode() && lvt_2_1_ % 2 != 0) {
            ++lvt_2_1_;
        }

        return lvt_2_1_;
    }

}
