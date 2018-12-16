package team.hdt.neutronia_legacy.base.client.gui.screens;

import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;

public class ConfigLinkScreen extends GuiConfirmOpenLink {

    GuiScreen parent;

    public ConfigLinkScreen(GuiScreen parentScreenIn, String url) {
        super(parentScreenIn, url, 0, true);
        parent = parentScreenIn;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == 1) // Esc
            returnToParent();
    }

    void returnToParent() {
        mc.displayGuiScreen(parent);

        if (mc.currentScreen == null)
            mc.setIngameFocus();
    }

}
