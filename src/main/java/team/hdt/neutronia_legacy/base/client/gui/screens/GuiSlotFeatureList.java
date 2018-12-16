package team.hdt.neutronia_legacy.base.client.gui.screens;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.client.GuiScrollingList;
import team.hdt.neutronia_legacy.base.groups.Component;

import java.util.ArrayList;

public class GuiSlotFeatureList extends GuiScrollingList {

    public final int listWidth = super.listWidth;
    public final int listHeight = super.listHeight;
    public final int screenWidth = super.screenWidth;
    public final int screenHeight = super.screenHeight;
    public final int top = super.top;
    public final int bottom = super.bottom;
    public final int right = super.right;
    public final int left = super.left;
    public int selectedIndex = -1;
    private GuiFeatureList parent;
    private ArrayList<Component> mods;

    public GuiSlotFeatureList(GuiFeatureList parent, ArrayList<Component> mods, int listWidth, int slotHeight) {
        super(parent.getMinecraftInstance(), listWidth, parent.height, 32, parent.height - 88 + 4, 10, slotHeight, parent.width, parent.height);
        this.parent = parent;
        this.mods = mods;
    }

    @Override
    protected int getSize() {
        return mods.size();
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick) {
        this.parent.selectModIndex(index);
    }

    @Override
    protected boolean isSelected(int index) {
        return this.parent.modIndexSelected(index);
    }

    @Override
    protected void drawBackground() {
        this.parent.drawDefaultBackground();
    }

    @Override
    protected int getContentHeight() {
        return (this.getSize()) * 35 + 1;
    }

    ArrayList<Component> getMods() {
        return mods;
    }

    @Override
    protected void drawSlot(int idx, int right, int top, int height, Tessellator tess) {
        Component mc = mods.get(idx);
        String name = StringUtils.stripControlCodes(mc.configName);
        FontRenderer font = this.parent.getFontRenderer();

        if (!mc.stateManager.enabled || !mc.stateManager.enabledByDefault) {
            font.drawString(font.trimStringToWidth(name, listWidth - 10), this.left + 3, top + 2, 0xFF2222);
            font.drawString(font.trimStringToWidth("DISABLED", listWidth - 10), this.left + 3, top + 22, 0xFF2222);
        } else {
            font.drawString(font.trimStringToWidth(name, listWidth - 10), this.left + 3, top + 2, 0xFFFFFF);
        }
    }
}