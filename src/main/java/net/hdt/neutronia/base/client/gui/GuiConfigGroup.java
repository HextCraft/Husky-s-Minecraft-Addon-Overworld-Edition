package net.hdt.neutronia.base.client.gui;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.Group;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiConfigGroup extends GuiConfigBase {

    private static final int FEATURES_PER_PAGE = 12;

    private final Group group;
    private final List<Component> components;
    private int page = 0;
    private int totalPages;

    private GuiButton left, right;

    GuiConfigGroup(GuiScreen parent, Group group) {
        super(parent);
        this.group = group;

        components = new ArrayList<>();
        group.forEachComponent(components::add);
        Collections.sort(components);

        totalPages = (components.size() - 1) / FEATURES_PER_PAGE + 1;
    }

    public void initGui() {
        super.initGui();

        title += " - " + I18n.translateToLocal("neutronia.config.group." + group.name.toLowerCase()) + " (" + components.size() + ")";

        int x = width / 2 - 100;
        int y = height / 6 + 167;

        buttonList.add(backButton = new GuiButton(0, x, y, 200, 20, I18n.translateToLocal("gui.done")));

        if (totalPages > 1) {
            x = width / 2;
            y = height / 6 - 12;
            buttonList.add(left = new GuiButton(0, x - 40, y, 20, 20, "<"));
            buttonList.add(right = new GuiButton(0, x + 20, y, 20, 20, ">"));
        }

        addFeatureButtons();
    }

    private void addFeatureButtons() {
        int startX = width / 2 - 195;
        int startY = height / 6 + 20;

        buttonList.removeIf((b) -> b instanceof ConfigSettingsButton || b instanceof FeatureSettingsButton);

        int x, y;

        int start = page * FEATURES_PER_PAGE;
        for (int i = start; i < Math.min(start + FEATURES_PER_PAGE, components.size()); i++) {
            int j = i - start;
            x = startX + j % 2 * 200;
            y = startY + j / 2 * 22;

            Component component = components.get(i);

            buttonList.add(new ConfigSettingsButton(x + 150, y, component.prop, true, component.getComponentIngameConfigName()));

            if (GroupLoader.config.hasCategory(component.configCategory))
                buttonList.add(new FeatureSettingsButton(x + 170, y, component.configCategory));
        }

        if (left != null) {
            left.enabled = (page > 0);
            right.enabled = (page < totalPages - 1);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if (button instanceof FeatureSettingsButton) {
            FeatureSettingsButton featureButton = (FeatureSettingsButton) button;
            mc.displayGuiScreen(new ConfigCategory(this, featureButton.category));
        } else if (button == left || button == right) {
            if (button == left)
                page = Math.max(page - 1, 0);
            else page = Math.min(page + 1, totalPages - 1);

            addFeatureButtons();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

//        drawRect(0, 0, Minecraft.getMinecraft().displayWidth, 100, Color.LIGHT_GRAY.getRGB());

        if (totalPages > 1) {
            int x = width / 2;
            int y = height / 6 - 7;
            drawCenteredString(mc.fontRenderer, (page + 1) + "/" + totalPages, x, y, 0xFFFFFF);
        }

        /*for(int i = 0; i < components.size(); i++) {
            if(components.size() < 2)
                drawRect(0, 0, Minecraft.getMinecraft().displayWidth, 100, Color.WHITE.getRGB());
            else
                drawRect(0, 0, Minecraft.getMinecraft().displayWidth, 100 * i, Color.WHITE.getRGB());
            mc.getTextureManager().bindTexture(new ResourceLocation("neutronia", "textures/config_gui/test_icon.png"));
            drawModalRectWithCustomSizedTexture(10, 10, 128, 128, 80, 80, 128, 128);
        }*/

        if (mayRequireRestart) {
            String s = I18n.translateToLocal("neutronia.config.needrestart");
            drawCenteredString(mc.fontRenderer, s, width / 2, backButton.y + 22, 0xFFFF00);
        }
    }

}
