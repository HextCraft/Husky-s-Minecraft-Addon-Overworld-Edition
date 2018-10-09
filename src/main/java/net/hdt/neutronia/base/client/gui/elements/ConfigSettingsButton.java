package net.hdt.neutronia.base.client.gui.elements;

import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.config.Property.Type;

public class ConfigSettingsButton extends GuiButton {

    public final Property prop;
    final boolean labeled;

    public ConfigSettingsButton(int x, int y, Property prop, boolean labeled) {
        this(x, y, prop, labeled, prop.getName());
    }

    public ConfigSettingsButton(int x, int y, Property prop, boolean labeled, String label) {
        super(0, x, y, 20, 20, label);
        this.prop = prop;
        this.labeled = labeled;

        if (prop.getType() != Type.BOOLEAN)
            throw new IllegalArgumentException("Property type must be BOOLEAN");
    }

    @Override
    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        if (labeled) {
            int width = fontRendererIn.getStringWidth(text);
            fontRendererIn.drawStringWithShadow(text, this.x - width - 5, y, color);
        }

        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(LibMisc.GENERAL_ICONS_RESOURCE);
        int u = 16;
        if (prop.getBoolean())
            u = 0;

        drawTexturedModalRect(this.x + 2, this.y + 2, u, 228, 16, 16);
    }

}
