package net.hdt.neutronia.base.client.gui.screens;

import net.hdt.neutronia.base.client.gui.GuiConfigBase;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

import java.io.IOException;

public class FirstLoadScreen extends GuiConfigBase {

    public FirstLoadScreen(GuiScreen parent) {
        super(parent);
    }

    @Override
    public void initGui() {
        super.initGui();

        title += " - " + I18n.translateToLocal("neutronia.config.firstload");

        int x = width / 2 - 100;
        int y = height / 6;

        buttonList.add(backButton = new GuiButton(0, x, y + 167, 200, 20, I18n.translateToLocal("neutronia.config.skip")));
        buttonList.add(new GuiButton(1, x, y + 142, 98, 20, I18n.translateToLocal("neutronia.config.configure")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        switch (button.id) {
            case 0:
                mc.displayGuiScreen(new MainMenu());
                break;
            case 1: // Configure
                mc.displayGuiScreen(new GuiNeutroniaConfig(parent));
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        int x = width / 2;
        int y = height / 6;
        for (int i = 0; i < 10; i++) {
            String s = I18n.translateToLocal("neutronia.config.firstloadinfo" + i);
            if (i == 5)
                s = (TextFormatting.RED.toString() + TextFormatting.UNDERLINE.toString() + s);

            drawCenteredString(fontRenderer, s, x, y, 0xFFFFFF);

            y += 10;
            if (i == 2 || i == 4)
                y += 8;
        }
    }

}
