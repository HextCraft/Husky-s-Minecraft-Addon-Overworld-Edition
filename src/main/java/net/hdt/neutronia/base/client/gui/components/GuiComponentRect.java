package net.hdt.neutronia.base.client.gui.components;

import net.hdt.neutronia.api.IValueReceiver;

public class GuiComponentRect extends GuiComponentResizable implements IValueReceiver<Integer> {

    private final int mask;
    private int color;

    public GuiComponentRect(int x, int y, int width, int height, int color) {
        this(x, y, width, height, color, 0xFF000000);
    }

    public GuiComponentRect(int x, int y, int width, int height, int color, int mask) {
        super(x, y, width, height);
        this.mask = mask;
        this.color = color | mask;
    }

    public int getColorForRender() {
        return color;
    }

    @Override
    public void render(int offsetX, int offsetY, int mouseX, int mouseY) {
        int oX = x + offsetX;
        int oY = y + offsetY;
        drawRect(oX, oY, oX + width, oY + height, getColorForRender());
    }

    @Override
    public void setValue(Integer color) {
        this.color = color | mask;
    }
}