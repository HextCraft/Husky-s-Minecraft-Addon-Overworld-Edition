package net.hdt.neutronia.base.client.gui.screens;

import net.hdt.neutronia.base.groups.Group;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.client.GuiScrollingList;

import java.util.ArrayList;

public class GuiSlotGroupList extends GuiScrollingList {

    public final int listWidth = super.listWidth;
    public final int listHeight = super.listHeight;
    public final int screenWidth = super.screenWidth;
    public final int screenHeight = super.screenHeight;
    public final int top = super.top;
    public final int bottom = super.bottom;
    public final int right = super.right;
    public final int left = super.left;
    public int selectedIndex = -1;
    private GuiGroupList parent;
    private ArrayList<Group> mods;

    public GuiSlotGroupList(GuiGroupList parent, ArrayList<Group> mods, int listWidth, int slotHeight) {
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

    ArrayList<Group> getMods() {
        return mods;
    }

    @Override
    protected void drawSlot(int idx, int right, int top, int height, Tessellator tess) {
        Group mc = mods.get(idx);
        String name = StringUtils.stripControlCodes(mc.getName());
        FontRenderer font = this.parent.getFontRenderer();

        if (!mc.enabled || !mc.enabledByDefault) {
            font.drawString(font.trimStringToWidth(name, listWidth - 10), this.left + 21, top + 2, 0xFF2222);
            font.drawString(font.trimStringToWidth("Disabled", listWidth - 10), this.left + 21, top + 22, 0xFF2222);
        } else {
            font.drawString(font.trimStringToWidth(name, listWidth - 10), this.left + 21, top + 8, 0xFFFFFF);
        }

        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableDepth();
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(mc.getIconStack(), this.left + 3, top + 4);
    }
}