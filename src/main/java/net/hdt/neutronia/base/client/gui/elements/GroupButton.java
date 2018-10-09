package net.hdt.neutronia.base.client.gui.elements;

import net.hdt.neutronia.base.groups.Group;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class GroupButton extends GuiButton {

    public final Group group;

    public GroupButton(int x, int y, Group group) {
        super(0, x, y, 150, 20, I18n.translateToLocal("neutronia.config.group." + group.getName().toLowerCase().replace(" ", "_")));
        this.group = group;
    }

    public GroupButton(int id, int x, int y, int width, int height, Group group) {
        super(id, x, y, width, height, I18n.translateToLocal("neutronia.config.group." + group.getName().toLowerCase().replace(" ", "_")));
        this.group = group;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;

            if (packedFGColour != 0) {
                j = packedFGColour;
            } else if (!this.enabled) {
                j = 10526880;
            } else if (this.hovered) {
                j = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);

            ItemStack stack = group.getIconStack();
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.enableDepth();
            mc.getRenderItem().renderItemIntoGUI(stack, x + 6, y + 2);
        }
    }

    @Override
    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        super.drawCenteredString(fontRendererIn, text, x + 14, y, color);
    }

}
