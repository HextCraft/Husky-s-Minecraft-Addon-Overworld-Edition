package team.hdt.neutronia.base.client.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import team.hdt.neutronia.base.lib.LibMisc;

public class FeatureSettingsButton extends GuiButton {

    public final String category;

    public FeatureSettingsButton(int x, int y, String category) {
        super(0, x, y, 20, 20, "C");
        this.category = category;
    }

    @Override
    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(LibMisc.GENERAL_ICONS_RESOURCE);
        drawTexturedModalRect(this.x + 2, this.y + 2, 32, 228, 16, 16);
    }

}
