package net.hdt.neutronia.base.client.gui.components;

import com.google.common.collect.ImmutableList;
import net.hdt.neutronia.base.client.gui.IComponentParent;
import net.hdt.neutronia.base.client.gui.Icon;
import net.hdt.neutronia.base.client.gui.listener.IKeyTypedListener;
import net.hdt.neutronia.base.client.gui.listener.IMouseDownListener;
import net.hdt.neutronia.base.client.gui.listener.IMouseDragListener;
import net.hdt.neutronia.base.client.gui.listener.IMouseUpListener;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class BaseComponent extends Gui {

    public final static ResourceLocation WIDGETS = LibMisc.location("textures/gui/components.png");
    protected int x;
    protected int y;
    protected boolean enabled = true;
    protected IComponentParent parent;
    private IKeyTypedListener keyListener;
    private IMouseDownListener mouseDownListener;
    private IMouseUpListener mouseUpListener;
    private IMouseDragListener mouseDragListener;

    public BaseComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected void bindComponentsSheet() {
        parent.bindTexture(WIDGETS);
    }

    public void init(IComponentParent parent) {
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX < x + getWidth() && mouseY >= y && mouseY < y + getHeight();
    }

    public void setListener(IKeyTypedListener keyListener) {
        this.keyListener = keyListener;
    }

    public void setListener(IMouseDownListener mouseDownListener) {
        this.mouseDownListener = mouseDownListener;
    }

    public void setListener(IMouseUpListener mouseUpListener) {
        this.mouseUpListener = mouseUpListener;
    }

    public void setListener(IMouseDragListener mouseDragListener) {
        this.mouseDragListener = mouseDragListener;
    }

    public void render(int offsetX, int offsetY, int mouseX, int mouseY) {
    }

    public void renderOverlay(int offsetX, int offsetY, int mouseX, int mouseY) {
    }

    public void keyTyped(char keyChar, int keyCode) {
        if (keyListener != null) keyListener.componentKeyTyped(this, keyChar, keyCode);
    }

    public void mouseDown(int mouseX, int mouseY, int button) {
        if (mouseDownListener != null) mouseDownListener.componentMouseDown(this, mouseX, mouseY, button);
    }

    public void mouseUp(int mouseX, int mouseY, int button) {
        if (mouseUpListener != null) mouseUpListener.componentMouseUp(this, mouseX, mouseY, button);
    }

    public void mouseDrag(int mouseX, int mouseY, int button, /* love you */long time) {
        if (mouseDragListener != null) mouseDragListener.componentMouseDrag(this, mouseX, mouseY, button, time);
    }

    public boolean isTicking() {
        return false;
    }

    public void tick() {
    }

    protected void drawItemStack(@Nonnull ItemStack stack, int x, int y, String overlayText) {
        if (stack.isEmpty()) return;

        RenderHelper.enableGUIStandardItemLighting();
        final RenderItem itemRenderer = parent.getItemRenderer();
        GlStateManager.translate(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRenderer.zLevel = 200.0F;

        FontRenderer font = null;
        font = stack.getItem().getFontRenderer(stack);
        if (font == null) font = parent.getFontRenderer();

        itemRenderer.renderItemAndEffectIntoGUI(stack, x, y);
        itemRenderer.renderItemOverlayIntoGUI(font, stack, x, y, overlayText);
        this.zLevel = 0.0F;
        itemRenderer.zLevel = 0.0F;
        RenderHelper.disableStandardItemLighting();
    }

    protected void drawItemStack(@Nonnull ItemStack stack, int x, int y) {
        if (stack.isEmpty()) return;

        RenderHelper.enableGUIStandardItemLighting();
        final RenderItem itemRenderer = parent.getItemRenderer();
        GlStateManager.translate(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRenderer.zLevel = 200.0F;

        itemRenderer.renderItemAndEffectIntoGUI(stack, x, y);
        this.zLevel = 0.0F;
        itemRenderer.zLevel = 0.0F;
        RenderHelper.disableStandardItemLighting();
    }

    protected void drawSprite(Icon icon, int x, int y, int width, int height) {
        parent.bindTexture(icon.texture);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x + 0, y + height, this.zLevel).tex(icon.minU, icon.maxV).endVertex();
        worldrenderer.pos(x + width, y + height, this.zLevel).tex(icon.maxU, icon.maxV).endVertex();
        worldrenderer.pos(x + width, y + 0, this.zLevel).tex(icon.maxU, icon.minV).endVertex();
        worldrenderer.pos(x + 0, y + 0, this.zLevel).tex(icon.minU, icon.minV).endVertex();
        tessellator.draw();
    }

    protected void drawSprite(Icon icon, int x, int y) {
        drawSprite(icon, x, y, icon.width, icon.height);
    }

    protected void drawHoveringText(List<String> textLines, int x, int y) {
        parent.drawHoveringText(textLines, x, y);
    }

    protected void drawHoveringText(String line, int x, int y) {
        parent.drawHoveringText(ImmutableList.of(line), x, y);
    }
}