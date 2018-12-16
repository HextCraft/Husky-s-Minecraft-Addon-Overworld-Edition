package team.hdt.neutronia_legacy.base.client.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class ModuleFold extends GuiScreen {

    private String name, desc;
    private GuiButton unfoldButton;
    private ItemStack stack;
    private int headerHeight = 0;
    private int innerHeight;
    private boolean unfolded = false;
    private int y;
    private int scroll = 0;

    public ModuleFold(String name, String desc, ItemStack stack) {
        this.name = name;
        this.desc = desc;
        this.stack = stack;
        this.unfoldButton = new GuiUnfoldButton(0, 6, this);
        this.buttonList.add(this.unfoldButton);
        this.innerHeight = 50;
    }

    public List<GuiButton> getButtonList() {
        return this.buttonList;
    }

    public boolean isUnfolded() {
        return this.unfolded;
    }

    public void setUnfolded(boolean unfolded) {
        this.unfolded = unfolded;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonList.add(this.unfoldButton);
    }

    @Override
    public void setWorldAndResolution(Minecraft mcIn, int w, int h) {
        super.setWorldAndResolution(mcIn, w, h);
        this.unfoldButton.x = this.width - 32;
        this.headerHeight = 15 + this.mc.fontRenderer.listFormattedStringToWidth(this.desc, this.width - 30).size() * 9;
    }

    public int getHeight() {
        return this.headerHeight + (this.unfolded ? this.innerHeight : 0) + 5;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mc.fontRenderer.drawString(TextFormatting.BOLD + this.name, 22, this.y + 4 - this.scroll, 0xFFFFFF);
        this.mc.fontRenderer.drawSplitString(this.desc, 22, this.y + 16 - this.scroll, this.width - 30, 0xFFFFFF);
        this.drawGradientRect(0, this.y + this.getHeight() - 1 - this.scroll, this.width, this.y + this.getHeight() - this.scroll, 0xFFFFFFFF, 0xFFFFFFFF);

        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableDepth();
        mc.getRenderItem().renderItemIntoGUI(stack, 2, y + 5 - this.scroll);

        if (unfolded) {
            mc.fontRenderer.drawString("Test", 22, 50, 0xFFFFFF);
        }

    }

    public void setY(int y) {
        this.y = y;
    }

    public int getScroll() {
        return this.scroll;
    }

    public void setScroll(int scroll) {
        this.scroll = scroll;
        this.unfoldButton.y = this.y - this.scroll + 2;
    }

    public static class GuiUnfoldButton extends GuiButton {

        public ModuleFold fold;

        public GuiUnfoldButton(int x, int y, ModuleFold fold) {
            super(-1, x, y, 20, 20, "<");
            this.fold = fold;
        }

    }

}