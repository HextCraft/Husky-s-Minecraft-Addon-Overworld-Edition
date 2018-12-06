package team.hdt.neutronia.base.client.gui.screens;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.base.client.gui.ConfigCategory;
import team.hdt.neutronia.base.client.gui.GuiConfigBase;
import team.hdt.neutronia.base.client.gui.GuiConfigGroup;
import team.hdt.neutronia.base.client.gui.elements.ColoredButton;
import team.hdt.neutronia.base.client.gui.elements.ConfigSettingsButton;
import team.hdt.neutronia.base.client.gui.elements.GroupButton;
import team.hdt.neutronia.base.groups.GlobalConfig;
import team.hdt.neutronia.base.groups.Group;
import team.hdt.neutronia.base.groups.GroupLoader;
import team.hdt.neutronia.base.lib.LibMisc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SideOnly(Side.CLIENT)
public class RootConfigGui extends GuiConfigBase {

    private static int MODULES_PER_PAGE = 8;
    private final List<Group> groups;
    private ResourceLocation ICONS = new ResourceLocation(LibMisc.MOD_ID, "textures/misc/config_screen_misc.png");
    private boolean nEnabled;
    private int page = 0;
    private int totalPages;
    private GuiButton left, right;

    public RootConfigGui(GuiScreen parent) {
        super(parent);
        groups = new ArrayList<>(GroupLoader.enabledGroups);
        Collections.sort(groups);
        nEnabled = GlobalConfig.enableNButton;
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

        buttonList.removeIf((b) -> b instanceof GroupButton || b instanceof ConfigSettingsButton);

        int start = page * MODULES_PER_PAGE;
        for (int j = start; j < Math.min(start + MODULES_PER_PAGE, groups.size()); j++) {
            int k = j - start;
            x = startX + k % 2 * 180;
            y = startY + k / 2 * 35;
            Group group = groups.get(j);
            buttonList.add(new GroupButton(x, y, group));
            buttonList.add(new ConfigSettingsButton(x + 150, y, group.prop, false));
        }

        if (left != null) {
            left.enabled = (page > 0);
            right.enabled = (page < totalPages - 1);
        }

        x = width / 2;
        y = startY + 117;
        buttonList.add(new ConfigSettingsButton(x + 85, y + 22, GlobalConfig.NButtonProp, true, I18n.translateToLocal("neutronia.config.enableq")));
        buttonList.add(new GuiButton(1, x - 100, y + 22, 98, 20, I18n.translateToLocal("neutronia.config.general")));
//        buttonList.add(new GuiButton(2, x  + 2, y + 22, 98, 20, I18n.translateToLocal("neutronia.config.import")));

//        buttonList.add(new CustomIconButton(3, x - 100, y + 44, 0, 0, 20, 20, CUSTOM_BUTTON_ICONS/*, I18n.translateToLocal("neutronia.config.opensite")*/));
//        buttonList.add(new CustomIconButton(4, x - 40, y + 44, 0, y + 44, 20, 20, CUSTOM_BUTTON_ICONS/*, I18n.translateToLocal("neutronia.config.discord")*/));
//        buttonList.add(new CustomIconButton(5, x + 36, y + 44, 0, y + 44 + 44, 20, 20, CUSTOM_BUTTON_ICONS/*, I18n.translateToLocal("neutronia.config.twitter")*/));
        buttonList.add(new ColoredButton(3, x - 100, y + 44, 54, I18n.translateToLocal("neutronia.config.opensite"), 0x4078c0));
        buttonList.add(new ColoredButton(5, x - 40, y + 44, 54, I18n.translateToLocal("neutronia.config.discord"), 0x7289da));
        buttonList.add(new ColoredButton(5, x + 36, y + 44, 54, I18n.translateToLocal("neutronia.config.twitter"), 0x55acee));

        buttonList.add(backButton = new GuiButton(0, x - 100, y + 66, 205, 20, I18n.translateToLocal("gui.done")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        String s = null;
        if (mayRequireRestart)
            s = I18n.translateToLocal("neutronia.config.needrestart");
        else if (nEnabled && !GlobalConfig.enableNButton)
            s = I18n.translateToLocal("neutronia.config.qdisabled");

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

        if (button instanceof GroupButton) {
            GroupButton moduleButton = (GroupButton) button;
            mc.displayGuiScreen(new GuiConfigGroup(this, moduleButton.group));
        } else if (button == left || button == right) {
            if (button == left)
                page = Math.max(page - 1, 0);
            else page = Math.min(page + 1, totalPages - 1);

            addFeatureButtons();
        } else switch (button.id) {
            case 1: // General Settings
                mc.displayGuiScreen(new ConfigCategory(this, "_global"));
                break;
            case 3: // Open Website
                tryOpenWebsite(LibMisc.MOD_WEBSITE);
                break;
        }
    }

}