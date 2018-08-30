package net.hdt.neutronia.base.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
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
    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(icon);
        drawTexturedModalRect(this.x + 2, this.y + 2, textureX, textureY, iconWidth, iconHeight);
    }

}