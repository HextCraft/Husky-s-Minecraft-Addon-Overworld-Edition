package team.hdt.neutronia_legacy.base.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class IconRenderer {

    private final Minecraft minecraft;
    private final double zLevel;
    private RenderItem itemRenderer;

    public IconRenderer(Minecraft minecraft, double zLevel) {

        this.minecraft = minecraft;
        this.zLevel = zLevel;
        this.itemRenderer = minecraft.getRenderItem();
    }

    public void renderItemStackIcon(int renderX, int renderY, ItemStack itemStack) {
        this.setupRender(renderX + 1, renderY + 1, 0, 0);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        if (itemStack != null) {
            RenderHelper.enableGUIStandardItemLighting();
            this.itemRenderer.renderItemIntoGUI(itemStack, renderX + 2, renderY + 2);
            RenderHelper.disableStandardItemLighting();
        }
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

    private void setupRender(int xBase, int yBase, int uBase, int vBase) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(Gui.STAT_ICONS);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
        wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        wr.pos((double) (xBase), (double) (yBase + 18), zLevel).tex((double) ((float) (uBase) * 0.0078125F), (double) ((float) (vBase + 18) * 0.0078125F)).endVertex();
        wr.pos((double) (xBase + 18), (double) (yBase + 18), zLevel).tex((double) ((float) (uBase + 18) * 0.0078125F), (double) ((float) (vBase + 18) * 0.0078125F)).endVertex();
        wr.pos((double) (xBase + 18), (double) (yBase), zLevel).tex((double) ((float) (uBase + 18) * 0.0078125F), (double) ((float) (vBase) * 0.0078125F)).endVertex();
        wr.pos((double) (xBase), (double) (yBase), zLevel).tex((double) ((float) (uBase) * 0.0078125F), (double) ((float) (vBase) * 0.0078125F)).endVertex();
        wr.finishDrawing();
    }
}