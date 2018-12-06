package team.hdt.neutronia.base.client.gui.components;

import org.lwjgl.input.Mouse;
import team.hdt.neutronia.base.client.gui.utils.BoxRenderer;

public abstract class GuiComponentButton extends GuiComponentResizable {

    private static final BoxRenderer BOX_RENDERER_NORMAL = new BoxRenderer(0, 10);
    private static final BoxRenderer BOX_RENDERER_PRESSED = new BoxRenderer(20, 10);
    private static final BoxRenderer BOX_RENDERER_DISABLED = new BoxRenderer(40, 10);
    protected boolean buttonEnabled = true;
    private int borderColor;

    public GuiComponentButton(int x, int y, int width, int height, int borderColor) {
        super(x, y, width, height);
        this.borderColor = borderColor;
    }

    public boolean isButtonEnabled() {
        return buttonEnabled;
    }

    public void setButtonEnabled(boolean enabled) {
        this.buttonEnabled = enabled;
    }

    @Override
    public void render(int offsetX, int offsetY, int mouseX, int mouseY) {
        boolean pressed = isMouseOver(mouseX, mouseY) && Mouse.isButtonDown(0);
        BoxRenderer box = buttonEnabled ? (pressed ? BOX_RENDERER_PRESSED : BOX_RENDERER_NORMAL) : BOX_RENDERER_DISABLED;
        bindComponentsSheet();
        box.render(this, x + offsetX, y + offsetY, width, height, borderColor);
        renderContents(offsetX, offsetY, mouseX, mouseY, pressed);
    }

    protected abstract void renderContents(int offsetX, int offsetY, int mouseX, int mouseY, boolean pressed);

}