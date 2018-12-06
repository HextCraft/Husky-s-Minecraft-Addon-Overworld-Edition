package team.hdt.neutronia.base.client.gui.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.client.GuiScrollingList;
import net.minecraftforge.fml.common.FMLLog;
import org.lwjgl.input.Mouse;
import team.hdt.neutronia.base.client.gui.GuiConfigGroup;
import team.hdt.neutronia.base.groups.Group;
import team.hdt.neutronia.base.groups.GroupLoader;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static net.minecraft.util.text.TextFormatting.WHITE;

public class GuiGroupList extends GuiScreen {

    private GuiScreen mainMenu;
    private GuiSlotGroupList modList;
    private GuiScrollingList modInfo;
    private int selected = -1;
    private Group selectedMod;
    private int listWidth;
    private ArrayList<Group> groups;
    private GuiButton configModButton;
    private GuiButton disableModButton;
    private int buttonMargin = 1;
    private int numButtons = SortType.values().length;
    private String lastFilterText = "";
    private GuiTextField search;
    private boolean sorted = false;
    private SortType sortType = SortType.NORMAL;

    /**
     * @param mainMenu
     */
    public GuiGroupList(GuiScreen mainMenu) {
        this.mainMenu = mainMenu;
        this.groups = new ArrayList<>();
        groups.addAll(GroupLoader.groups);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    @Override
    public void initGui() {
        int slotHeight = 30;
        for (Group group : groups) {
            listWidth = Math.max(listWidth, getFontRenderer().getStringWidth(group.getName()) + 10);
        }
        listWidth = Math.min(listWidth, 160);
        this.modList = new GuiSlotGroupList(this, groups, listWidth, slotHeight);

        this.buttonList.add(new GuiButton(6, ((modList.right + this.width) / 2) - 100, this.height - 38, I18n.format("gui.done")));
        configModButton = new GuiButton(20, 10, this.height - 49, this.listWidth, 20, "Config");
        disableModButton = new GuiButton(21, 10, this.height - 27, this.listWidth, 20, "Disable");
        this.buttonList.add(configModButton);
        this.buttonList.add(disableModButton);

        search = new GuiTextField(0, getFontRenderer(), 12, modList.bottom + 17, modList.listWidth - 4, 14);
        search.setFocused(true);
        search.setCanLoseFocus(true);

        updateCache();
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    @Override
    protected void mouseClicked(int x, int y, int button) throws IOException {
        super.mouseClicked(x, y, button);
        search.mouseClicked(x, y, button);
        if (button == 1 && x >= search.x && x < search.x + search.width && y >= search.y && y < search.y + search.height) {
            search.setText("");
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    @Override
    protected void keyTyped(char c, int keyCode) throws IOException {
        super.keyTyped(c, keyCode);
        search.textboxKeyTyped(c, keyCode);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen() {
        super.updateScreen();
        search.updateCursorCounter();

        if (!search.getText().equals(lastFilterText)) {
            reloadMods();
            sorted = false;
        }

        if (!sorted) {
            reloadMods();
            Collections.sort(groups, sortType);
            selected = modList.selectedIndex = groups.indexOf(selectedMod);
            sorted = true;
        }
    }

    private void reloadMods() {
        ArrayList<Group> mods = modList.getMods();
        mods.clear();
        for (Group m : GroupLoader.enabledGroups) {
            // If it passes the filter, and is not a child mod
            if (m.getName().toLowerCase().contains(search.getText().toLowerCase())) {
                mods.add(m);
            }
        }
        this.groups = mods;
        lastFilterText = search.getText();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            SortType type = SortType.getTypeForButton(button);

            if (type != null) {
                for (GuiButton b : buttonList) {
                    if (SortType.getTypeForButton(b) != null) {
                        b.enabled = true;
                    }
                }
                button.enabled = false;
                sorted = false;
                sortType = type;
                this.groups = modList.getMods();
            } else {
                switch (button.id) {
                    case 6: {
                        this.mc.displayGuiScreen(this.mainMenu);
                        return;
                    }
                    case 20: {
                        try {
                            mc.displayGuiScreen(new GuiConfigGroup(this, selectedMod));
                        } catch (Exception e) {
                            FMLLog.log.error("There was a critical issue trying to build the config GUI for {}", selectedMod.getName(), e);
                        }
                        return;
                    }
                }
            }
        }
        super.actionPerformed(button);
    }

    public int drawLine(String line, int offset, int shifty) {
        this.fontRenderer.drawString(line, offset, shifty, 0xd7edea);
        return shifty + 10;
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.modList.drawScreen(mouseX, mouseY, partialTicks);
        if (this.modInfo != null)
            this.modInfo.drawScreen(mouseX, mouseY, partialTicks);

        int left = ((this.width - this.listWidth - 38) / 2) + this.listWidth + 30;
        this.drawCenteredString(this.fontRenderer, "Groups", left, 16, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);

        String text = I18n.format("fml.menu.mods.search");
        int x = ((10 + modList.right) / 2) - (getFontRenderer().getStringWidth(text) / 2);
        getFontRenderer().drawString(text, x, modList.bottom + 5, 0xFFFFFF);
        search.drawTextBox();
    }

    /**
     * Handles mouse input.
     */
    @Override
    public void handleMouseInput() throws IOException {
        int mouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;

        super.handleMouseInput();
        if (this.modInfo != null)
            this.modInfo.handleMouseInput(mouseX, mouseY);
        this.modList.handleMouseInput(mouseX, mouseY);
    }

    Minecraft getMinecraftInstance() {
        return mc;
    }

    FontRenderer getFontRenderer() {
        return fontRenderer;
    }

    public void selectModIndex(int index) {
        if (index == this.selected)
            return;
        this.selected = index;
        this.selectedMod = (index >= 0 && index <= groups.size()) ? groups.get(selected) : null;

        updateCache();
    }

    public boolean modIndexSelected(int index) {
        return index == selected;
    }

    private void updateCache() {
        configModButton.visible = false;
        disableModButton.visible = false;
        modInfo = null;

        if (selectedMod == null)
            return;

        List<String> lines = new ArrayList<String>();

        lines.add(WHITE + selectedMod.getName());

        lines.add(null);
        lines.add(selectedMod.desc);

        modInfo = new Info(this.width - this.listWidth - 30, lines);
    }

    private enum SortType implements Comparator<Group> {
        NORMAL(24),
        A_TO_Z(25) {
            @Override
            protected int compare(String name1, String name2) {
                return name1.compareTo(name2);
            }
        },
        Z_TO_A(26) {
            @Override
            protected int compare(String name1, String name2) {
                return name2.compareTo(name1);
            }
        };

        private int buttonID;

        private SortType(int buttonID) {
            this.buttonID = buttonID;
        }

        @Nullable
        public static SortType getTypeForButton(GuiButton button) {
            for (SortType t : values()) {
                if (t.buttonID == button.id) {
                    return t;
                }
            }
            return null;
        }

        protected int compare(String name1, String name2) {
            return 0;
        }

        @Override
        public int compare(Group o1, Group o2) {
            String name1 = StringUtils.stripControlCodes(o1.getName()).toLowerCase();
            String name2 = StringUtils.stripControlCodes(o2.getName()).toLowerCase();
            return compare(name1, name2);
        }
    }

    private class Info extends GuiScrollingList {

        private List<ITextComponent> lines = null;

        public Info(int width, List<String> lines) {
            super(GuiGroupList.this.getMinecraftInstance(),
                    width,
                    GuiGroupList.this.height,
                    32, GuiGroupList.this.height - 88 + 4,
                    GuiGroupList.this.listWidth + 20, 60,
                    GuiGroupList.this.width,
                    GuiGroupList.this.height);
            this.lines = resizeContent(lines);

            this.setHeaderInfo(true, getHeaderHeight());
        }

        @Override
        protected int getSize() {
            return 0;
        }

        @Override
        protected void elementClicked(int index, boolean doubleClick) {
        }

        @Override
        protected boolean isSelected(int index) {
            return false;
        }

        @Override
        protected void drawBackground() {
        }

        @Override
        protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {
        }

        private List<ITextComponent> resizeContent(List<String> lines) {
            List<ITextComponent> ret = new ArrayList<ITextComponent>();
            for (String line : lines) {
                if (line == null) {
                    ret.add(null);
                    continue;
                }

                ITextComponent chat = ForgeHooks.newChatWithLinks(line, false);
                int maxTextLength = this.listWidth - 8;
                if (maxTextLength >= 0) {
                    ret.addAll(GuiUtilRenderComponents.splitText(chat, maxTextLength, GuiGroupList.this.fontRenderer, false, true));
                }
            }
            return ret;
        }

        private int getHeaderHeight() {
            int height = 0;
            height += (lines.size() * 10);
            if (height < this.bottom - this.top - 8) height = this.bottom - this.top - 8;
            return height;
        }

        @Override
        protected void drawHeader(int entryRight, int relativeY, Tessellator tess) {
            int top = relativeY;

            for (ITextComponent line : lines) {
                if (line != null) {
                    GlStateManager.enableBlend();
                    GuiGroupList.this.fontRenderer.drawStringWithShadow(line.getFormattedText(), this.left + 4, top, 0xFFFFFF);
                    GlStateManager.disableAlpha();
                    GlStateManager.disableBlend();
                }
                top += 10;
            }
        }

        @Override
        protected void clickHeader(int x, int y) {
            if (y <= 0)
                return;

            int lineIdx = y / 10;
            if (lineIdx >= lines.size())
                return;

            ITextComponent line = lines.get(lineIdx);
            if (line != null) {
                int k = -4;
                for (ITextComponent part : line) {
                    if (!(part instanceof TextComponentString))
                        continue;
                    k += GuiGroupList.this.fontRenderer.getStringWidth(((TextComponentString) part).getText());
                    if (k >= x) {
                        GuiGroupList.this.handleComponentClick(part);
                        break;
                    }
                }
            }
        }
    }
}