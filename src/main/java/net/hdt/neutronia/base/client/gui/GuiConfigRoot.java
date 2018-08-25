package net.hdt.neutronia.base.client.gui;

import net.hdt.neutronia.base.groups.GlobalConfig;
import net.hdt.neutronia.base.groups.Group;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiConfigRoot extends GuiConfigBase {

    private static int MODULES_PER_PAGE = 8;
    private final List<Group> groups;
    boolean nEnabled;
    private int page = 0;
    private int totalPages;
    private GuiButton left, right;

    GuiConfigRoot(GuiScreen parent) {
        super(parent);

        groups = new ArrayList<>(GroupLoader.groups);
        groups.removeIf(module -> !module.enabled);
        Collections.sort(groups);

        nEnabled = GlobalConfig.enableNButton;

        System.out.println(groups.size());
        totalPages = (groups.size() - 1) / MODULES_PER_PAGE + 1;
    }

    @Override
    public void initGui() {
        super.initGui();

        int x, y;

        if (totalPages > 1) {
            x = width / 2;
            y = height / 6 - 12;
            buttonList.add(left = new GuiButton(0, x - 40, y, 20, 20, "<"));
            buttonList.add(right = new GuiButton(0, x + 20, y, 20, 20, ">"));
        }

        addFeatureButtons();
    }

    private void addFeatureButtons() {

        int x, y;

        int startX = width / 2 - 185;
        int startY = height / 5 + 3;

        buttonList.removeIf((b) -> b instanceof GuiButtonModule || b instanceof GuiButtonConfigSetting);

        int start = page * MODULES_PER_PAGE;
        for (int j = start; j < Math.min(start + MODULES_PER_PAGE, groups.size()); j++) {
            int k = j - start;
            x = startX + k % 2 * 180;
            y = startY + k / 2 * 22;
            Group group = groups.get(j);
            buttonList.add(new GuiButtonModule(x, y, group));
            buttonList.add(new GuiButtonConfigSetting(x + 150, y, group.prop, false));
        }

        if (left != null) {
            left.enabled = (page > 0);
            right.enabled = (page < totalPages - 1);
        }

        x = width / 2;
        y = startY + 113;
        buttonList.add(new GuiButtonConfigSetting(x + 155, y, GlobalConfig.NButtonProp, true, I18n.translateToLocal("neutronia.config.enableq")));
        buttonList.add(new GuiButton(1, x - 100, y + 22, 98, 20, I18n.translateToLocal("neutronia.config.general")));
//        buttonList.add(new GuiButton(2, x  + 2, y + 22, 98, 20, I18n.translateToLocal("neutronia.config.import")));

        buttonList.add(new GuiButtonColor(3, x - 100, y + 44, 64, I18n.translateToLocal("neutronia.config.opensite"), 0x48ddbc));
        buttonList.add(new GuiButtonColor(5, x + 36, y + 44, 64, I18n.translateToLocal("neutronia.config.discord"), 0xf96854));
        buttonList.add(new GuiButtonColor(5, x + 36, y + 44, 64, I18n.translateToLocal("neutronia.config.twitter"), 0xf96854));

        buttonList.add(backButton = new GuiButton(0, x - 100, y + 66, 200, 20, I18n.translateToLocal("gui.done")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        String s = null;
        if (mayRequireRestart)
            s = I18n.translateToLocal("neutronia.config.needrestart");
        else if (nEnabled && !GlobalConfig.enableNButton)
            s = I18n.translateToLocal("neutronia.config.qdisabled");

        /*for(int i = 0; i < groups.size(); i++) {
            int nextHeight = 79 * i;
            int nextHeightIcon = 60 * i;
            int nextHeightLine = 79 * i;
            ItemStack stack = groups.get(i).getIconStack();
            if(groups.size() < 2) {
                drawRect(80, 0, Minecraft.getMinecraft().displayWidth - 80, 80, Color.GRAY.getRGB());
                drawRect(0, 0, 80, 80, Color.WHITE.getRGB());
                RenderHelper.enableGUIStandardItemLighting();
                GlStateManager.enableDepth();
                mc.getRenderItem().renderItemIntoGUI(stack, 35, 40);
                drawString(fontRenderer, groups.get(i).name, 65, 20, 0xFFFFFF);
                drawString(fontRenderer, groups.get(i).desc, 65, 40, 0xFFFFFF);
                drawRect(80, 79, Minecraft.getMinecraft().displayWidth - 80, 80, Color.DARK_GRAY.getRGB());
            } else {
//                drawRect(0, 0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, Color.DARK_GRAY.getRGB());
                drawRect(60, 0, Minecraft.getMinecraft().displayWidth - 60, 59 + nextHeight, Color.GRAY.getRGB());
                drawRect(0, 0, 60, nextHeightIcon, Color.WHITE.getRGB());
                RenderHelper.enableGUIStandardItemLighting();
                GlStateManager.enableDepth();
                ZoomRenderHelper.renderZoomedStack(stack, this, mc, 8, 10 + nextHeightIcon);
                drawString(fontRenderer, groups.get(i).name, 85, 20 + nextHeightIcon, 0xFFFFFF);
                drawString(fontRenderer, groups.get(i).desc, 85, 40 + nextHeightIcon, 0xFFFFFF);
            }
        }*/

        if (totalPages > 1) {
            int x = width / 2;
            int y = height / 6 - 7;
            drawCenteredString(mc.fontRenderer, (page + 1) + "/" + totalPages, x, y, 0xFFFFFF);
        }

        if (s != null)
            drawCenteredString(mc.fontRenderer, s, width / 2, backButton.y + 22, 0xFFFF00);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if (button instanceof GuiButtonModule) {
            GuiButtonModule moduleButton = (GuiButtonModule) button;
            mc.displayGuiScreen(new GuiConfigGroup(this, moduleButton.group));
        } else if (button == left || button == right) {
            if (button == left)
                page = Math.max(page - 1, 0);
            else page = Math.min(page + 1, totalPages - 1);

            addFeatureButtons();
        } else switch (button.id) {
            case 1: // General Settings
                mc.displayGuiScreen(new GuiConfigCategory(this, "_global"));
                break;
            /*case 2: // Import Config
                mc.displayGuiScreen(new GuiConfigImport(this));
                break;*/
            case 3: // Open Website
                tryOpenWebsite(LibMisc.MOD_WEBSITE);
                break;
        }
    }

}
