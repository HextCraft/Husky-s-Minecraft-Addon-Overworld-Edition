package net.hdt.neutronia.base.client.gui.elements;

import net.hdt.neutronia.base.client.ContributorRewardHandler;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class NeutroniaButton extends ColoredButton {

    public NeutroniaButton(int x, int y) {
        super(-82392, x, y, 20, "N", 0x48ddbc);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);

        if(ContributorRewardHandler.localPatronTier > 0) {
            GlStateManager.color(1F, 1F, 1F);
            int tier = Math.min(4, ContributorRewardHandler.localPatronTier);
            int u = 256 - tier * 9;
            int v = 26;

            mc.renderEngine.bindTexture(LibMisc.GENERAL_ICONS_RESOURCE2);
            drawTexturedModalRect(x - 2, y - 2, u, v, 9, 9);
        }

    }

}