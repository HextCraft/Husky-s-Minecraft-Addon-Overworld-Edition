package net.hdt.neutronia.base.client.gui;

import com.google.common.collect.ImmutableSet;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.io.IOException;
import java.util.Set;

public class GuiConfigImport extends GuiConfigBase {

    boolean needsRestart = false;
    int disabledFeatures;
    GuiTextField textField;

    public GuiConfigImport(GuiScreen parent) {
        super(parent);
    }

    @Override
    public void initGui() {
        super.initGui();

        title += " - " + I18n.translateToLocal("neutronia.config.import");

        int x = width / 2 - 100;
        int y = height / 6;

//        GuiButton importButton = new GuiButton(1, x, y + 110, 200, 20, I18n.translateToLocal("neutronia.config.import"));
        buttonList.add(backButton = new GuiButton(0, x, y + 167, 200, 20, I18n.translateToLocal("gui.done")));
//        buttonList.add(importButton);
        buttonList.add(new GuiButton(2, x, y + 132, 200, 20, I18n.translateToLocal("neutronia.config.opensite")));

        textField = new GuiTextField(0, fontRenderer, x, y + 72, 200, 20);
        textField.setFocused(true);
        textField.setCanLoseFocus(false);
        textField.setMaxStringLength(Integer.MAX_VALUE);

        /*if (mc.world != null)
            importButton.enabled = false;*/
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        switch (button.id) {
            case 1: // Import/Quit
                if (!needsRestart) {
                    doImport();

                    if (needsRestart) {
                        button.displayString = I18n.translateToLocal("neutronia.config.close");
                        for (GuiButton b : buttonList)
                            if (b != button)
                                b.enabled = false;
                    }
                } else FMLCommonHandler.instance().exitJava(0, false);
                break;
            /*case 2: // Open Website
                tryOpenWebsite();
                break;*/
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (!needsRestart) {
            super.keyTyped(typedChar, keyCode);
            textField.textboxKeyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        textField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        textField.drawTextBox();

        int x = width / 2;
        for (int i = 0; i < 4; i++) {
            int y = 50 + i * 10;
            String key = "neutronia.config.importinfo" + i;
            if (i == 3) {
                if (Minecraft.IS_RUNNING_ON_MAC)
                    key += "m";

                y += 5;
            }

            drawCenteredString(mc.fontRenderer, I18n.translateToLocal(key), x, y, 0xFFFFFF);
        }

        if (needsRestart) {
            String s;
            if (disabledFeatures == 1)
                s = I18n.translateToLocalFormatted("neutronia.config.disabledcount1");
            else s = I18n.translateToLocalFormatted("neutronia.config.disabledcount", disabledFeatures);

            drawCenteredString(mc.fontRenderer, s, x, textField.y + 26, 0x00FF00);
        }

        if (mc.world != null)
            drawCenteredString(mc.fontRenderer, I18n.translateToLocal("neutronia.config.cantimport"), x, textField.y + 26, 0xFF0000);
    }

    private void doImport() {
        boolean changed = false;
        disabledFeatures = 0;

        String[] disables = textField.getText().trim().split(",");
        if (disables.length > 0) {
            Set<String> disabledSet = ImmutableSet.copyOf(disables);

            for (String name : GroupLoader.componentClassNames.keySet()) {
                Component f = GroupLoader.componentClassNames.get(name);
                boolean enabled = disabledSet.contains(name) != f.enabledByDefault;
                if (f.prop.getBoolean() != enabled)
                    f.prop.set(enabled);

                if (f.prop.hasChanged()) {
                    changed = true;
                    if (!enabled)
                        disabledFeatures++;
                }
            }
        }

        needsRestart = false;
        if (changed) {
            GroupLoader.loadConfig();
            needsRestart = true;
        }
    }

}
