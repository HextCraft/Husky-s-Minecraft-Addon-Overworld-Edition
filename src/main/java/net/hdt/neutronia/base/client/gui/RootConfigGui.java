package net.hdt.neutronia.base.client.gui;

import net.hdt.neutronia.base.groups.GlobalConfig;
import net.hdt.neutronia.base.groups.Group;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.base.lib.LibMisc;
import net.hdt.neutronia.base.util.Color;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RootConfigGui extends GuiConfigBase {

    protected final StandardPanel backgroundPanel = new StandardPanel();
    protected final StandardPanel presetPanel = new StandardPanel(0, 0, Color.GOLD, Color.BLACK, Color.GRAY);

    protected int anchorX;
    protected int anchorY;
    protected int regionWidth;
    protected int regionHeight;

    private static int MODULES_PER_PAGE = 10;
    private ResourceLocation CUSTOM_BUTTON_ICONS = new ResourceLocation(LibMisc.MOD_ID, "textures/misc/custom_button_icons.png");
    private final List<Group> groups;
    boolean nEnabled;
    private int page = 0;
    private int totalPages;
    private GuiButton left, right;

    protected static final int MARGIN = 10;
    protected static final int INSET = 5;
    protected static final int MAX_PRESETS_PAGE = 5;
    protected static final int BUTTON_WIDTH = 100;
    protected static final int BUTTON_HEIGHT = 20;
    protected static final int PRESET_BUTTON_WIDTH = 200;
    protected static final int PRESET_BUTTON_HEIGHT = 20;
    protected static final int NAV_BUTTON_WIDTH = 25;
    protected static final int NAV_BUTTON_INSET = 5;

    RootConfigGui(GuiScreen parent) {
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

//        addFeatureButtons();

        final int presetWidth = PRESET_BUTTON_WIDTH + INSET * 2;
        final int presetHeight = (int) (PRESET_BUTTON_HEIGHT * (MAX_PRESETS_PAGE + 1.5F)) + INSET;
        this.presetPanel.setWidth(presetWidth);
        this.presetPanel.setHeight(presetHeight);

        this.regionWidth = MARGIN * 2 + presetWidth + INSET + BUTTON_WIDTH;
        this.regionHeight = MARGIN * 2 + presetHeight + BUTTON_HEIGHT * 2;
        this.backgroundPanel.setWidth(this.regionWidth);
        this.backgroundPanel.setHeight(this.regionHeight);
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
            y = startY + k / 2 * 22;
            Group group = groups.get(j);
            buttonList.add(new GroupButton(x, y, group));
            buttonList.add(new ConfigSettingsButton(x + 150, y, group.prop, false));
        }

        if (left != null) {
            left.enabled = (page > 0);
            right.enabled = (page < totalPages - 1);
        }

        x = width / 2;
        y = startY + 113;
        buttonList.add(new ConfigSettingsButton(x + 85, y + 22, GlobalConfig.NButtonProp, true, I18n.translateToLocal("neutronia.config.enableq")));
        buttonList.add(new GuiButton(1, x - 100, y + 22, 98, 20, I18n.translateToLocal("neutronia.config.general")));
//        buttonList.add(new GuiButton(2, x  + 2, y + 22, 98, 20, I18n.translateToLocal("neutronia.config.import")));

        buttonList.add(new CustomIconButton(3, x - 100, y + 44, 0, 0, 20, 20, CUSTOM_BUTTON_ICONS/*, I18n.translateToLocal("neutronia.config.opensite")*/));
        buttonList.add(new CustomIconButton(5, x - 40, y + 44, 0, y + 44, 20, 20, CUSTOM_BUTTON_ICONS/*, I18n.translateToLocal("neutronia.config.discord")*/));
        buttonList.add(new CustomIconButton(5, x + 36, y + 44, 0, y + 44 + 44, 20, 20, CUSTOM_BUTTON_ICONS/*, I18n.translateToLocal("neutronia.config.twitter")*/));

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

//        mc.getTextureManager().bindTexture(new ResourceLocation("textures/blocks/dirt.png"));
//        drawTexturedModalRect(0, 0, 0, 0, this.width, this.height);

        if (totalPages > 1) {
            int x = width / 2;
            int y = height / 6 - 7;
            drawCenteredString(mc.fontRenderer, (page + 1) + "/" + totalPages, x, y, 0xFFFFFF);
        }

        if (s != null)
            drawCenteredString(mc.fontRenderer, s, width / 2, backButton.y + 22, 0xFFFF00);

        drawDefaultBackground();
        this.backgroundPanel.render(this.anchorX, this.anchorY, Panel.Reference.UPPER_LEFT);
        this.presetPanel.render(this.anchorX + MARGIN, this.anchorY + MARGIN + INSET * 3, Panel.Reference.UPPER_LEFT);
        super.drawScreen(mouseX, mouseY, partialTicks);
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
            /*case 2: // Import Config
                mc.displayGuiScreen(new GuiConfigImport(this));
                break;*/
            case 3: // Open Website
                tryOpenWebsite(LibMisc.MOD_WEBSITE);
                break;
        }
    }

}
