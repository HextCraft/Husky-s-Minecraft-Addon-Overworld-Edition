package team.hdt.neutronia.base.client.gui.components;

import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import team.hdt.neutronia.base.client.gui.utils.BoxRenderer;

import javax.annotation.Nonnull;

public class GuiComponentTab extends GuiComponentResizableComposite {

    private static final int FOLDED_WIDTH = 24;
    private static final int FOLDED_HEIGHT = 24;
    private static final BoxRenderer BOX_RENDERER = new BoxRenderer(0, 5) {
        @Override
        protected void renderTopLeftCorner(Gui gui) {
        }

        @Override
        protected void renderBottomLeftCorner(Gui gui, int height) {
        }

        @Override
        protected void renderLeftEdge(Gui gui, int height) {
        }
    };
    protected final int expandedWidth;
    protected final int expandedHeight;
    @Nonnull
    private final ItemStack iconStack;
    private boolean active = false;
    private double dWidth = FOLDED_WIDTH;
    private double dHeight = FOLDED_HEIGHT;
    private int color;

    public GuiComponentTab(int color, @Nonnull ItemStack iconStack, int expandedWidth, int expandedHeight) {
        super(-5, 0, FOLDED_WIDTH, FOLDED_HEIGHT);
        this.expandedWidth = expandedWidth;
        this.expandedHeight = expandedHeight;
        this.iconStack = iconStack;
        this.color = color;
    }

    @Override
    protected boolean areChildrenActive() {
        return active && width == expandedWidth && height == expandedHeight;
    }

    @Override
    public void renderComponentBackground(int offsetX, int offsetY, int mouseX, int mouseY) {
        double targetWidth = active ? expandedWidth : FOLDED_WIDTH;
        double targetHeight = active ? expandedHeight : FOLDED_HEIGHT;
        if (width != targetWidth) dWidth += (targetWidth - dWidth) / 4;
        if (height != targetHeight) dHeight += (targetHeight - dHeight) / 4;

        width = (int) Math.round(dWidth);
        height = (int) Math.round(dHeight);

        bindComponentsSheet();
        BOX_RENDERER.render(this, offsetX + x, offsetY + y, width, height, color);

        drawItemStack(iconStack, offsetX + x + 3, offsetY + y + 3);
    }

    public boolean isOrigin(int x, int y) {
        return x < FOLDED_WIDTH && y < FOLDED_WIDTH;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}