package net.hdt.neutronia.base.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class CustomIconButton extends GuiButton {

    private ResourceLocation icon;
    private int textureX, textureY, iconWidth, iconHeight;

    public CustomIconButton(int id, int xPos, int yPos, int textureX, int textureY, int iconWidth, int iconHeight, ResourceLocation icon) {
        this(id, xPos, yPos, textureX, textureY, iconWidth, iconHeight, icon, "");
    }

    public CustomIconButton(int id, int xPos, int yPos, int textureX, int textureY, int iconWidth, int iconHeight, ResourceLocation icon, String text) {
        super(id, xPos, yPos, iconWidth + 4, iconHeight + 4, text);
        this.icon = icon;
        this.textureX = textureX;
        this.textureY = textureY;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible && super.displayString.isEmpty()) {
            mc.getTextureManager().bindTexture(icon);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.x + this.width / 2, this.y, textureX, textureY + i * 20, iconWidth, iconHeight);
            this.mouseDragged(mc, mouseX, mouseY);
        } else {
            super.drawButton(mc, mouseX, mouseY, partialTicks);
        }
    }

}