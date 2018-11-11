package net.hdt.neutronia.base.client.gui.utils;

import com.google.common.collect.ImmutableList;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec2f;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import java.util.List;

@SideOnly(Side.CLIENT)
public final class GuiUtils {

    private static List<String> brandings;
    private static List<String> brandingsNoMC;

    private GuiUtils() {

    }

    public static GuiUtils instance() {
        return new GuiUtils();
    }

    public static void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        fontRendererIn.drawStringWithShadow(text, x - fontRendererIn.getStringWidth(text) / 2, y, color);
    }

    public static void drawString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        fontRendererIn.drawStringWithShadow(text, x, y, color);
    }

    public static void drawRect(int left, int top, int right, int bottom, int color) {
        if (left < right) {
            final int i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            final int j = top;
            top = bottom;
            bottom = j;
        }

        final float f3 = (color >> 24 & 255) / 255.0F;
        final float f = (color >> 16 & 255) / 255.0F;
        final float f1 = (color >> 8 & 255) / 255.0F;
        final float f2 = (color & 255) / 255.0F;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder vertexbuffer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(left, bottom, 0.0D).endVertex();
        vertexbuffer.pos(right, bottom, 0.0D).endVertex();
        vertexbuffer.pos(right, top, 0.0D).endVertex();
        vertexbuffer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {

        final float zLevel = 0.0F;

        final float f = (startColor >> 24 & 255) / 255.0F;
        final float f1 = (startColor >> 16 & 255) / 255.0F;
        final float f2 = (startColor >> 8 & 255) / 255.0F;
        final float f3 = (startColor & 255) / 255.0F;
        final float f4 = (endColor >> 24 & 255) / 255.0F;
        final float f5 = (endColor >> 16 & 255) / 255.0F;
        final float f6 = (endColor >> 8 & 255) / 255.0F;
        final float f7 = (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder t = tessellator.getBuffer();
        t.begin(7, DefaultVertexFormats.POSITION_COLOR);
        t.pos(left + right, top, zLevel).color(f1, f2, f3, f).endVertex();
        t.pos(left, top, zLevel).color(f1, f2, f3, f).endVertex();
        t.pos(left, top + bottom, zLevel).color(f5, f6, f7, f4).endVertex();
        t.pos(left + right, top + bottom, zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawTooltipBox(int x, int y, int w, int h, int bg, int grad1, int grad2) {
        drawGradientRect(x + 1, y, w - 1, 1, bg, bg);
        drawGradientRect(x + 1, y + h, w - 1, 1, bg, bg);
        drawGradientRect(x, y + 1, 1, h - 1, bg, bg);
        drawGradientRect(x + w, y + 1, 1, h - 1, bg, bg);
        drawGradientRect(x + 1, y + 2, 1, h - 3, grad1, grad2);
        drawGradientRect(x + w - 1, y + 2, 1, h - 3, grad1, grad2);
        drawGradientRect(x + 1, y + 1, w - 1, 1, grad1, grad1);
        drawGradientRect(x + 1, y + h - 1, w - 1, 1, grad2, grad2);
    }

    public static void drawTexturedModalRect(@Nonnull final ResourceLocation texture, final int x, final int y,
                                             final int width, final int height, @Nonnull final Vec2f u, @Nonnull final Vec2f v) {
        final float zLevel = 0F;

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(x + 0, y + height, zLevel).tex(u.x, v.x).endVertex();
        vertexbuffer.pos(x + width, y + height, zLevel).tex(u.y, v.x).endVertex();
        vertexbuffer.pos(x + width, y + 0, zLevel).tex(u.y, v.y).endVertex();
        vertexbuffer.pos(x + 0, y + 0, zLevel).tex(u.x, v.y).endVertex();
        tessellator.draw();
    }

    public static Vec2f calculateSpan(final int sheetDimension, final int first, final int second) {
        return new Vec2f(first / (float) sheetDimension, second / (float) sheetDimension);
    }

    public static void computeBranding() {
        if (brandings == null) {
            ImmutableList.Builder<String> brd = ImmutableList.builder();
            brd.add(I18n.format("fml.mainMenu.minecraft", Loader.instance().getMCVersionString()));
            brd.add(I18n.format("fml.mainMenu.mcp", Loader.instance().getMCPVersionString()));
            brd.add(I18n.format("fml.mainMenu.forge", ForgeVersion.getVersion()));
            brd.add(I18n.format("neutronia.mainMenu.version", LibMisc.MOD_NAME, LibMisc.VERSION));
            int tModCount = Loader.instance().getModList().size();
            int aModCount = Loader.instance().getActiveModList().size();
            brd.add(I18n.format("fml.menu.loadingMods", tModCount, tModCount != 1 ? "s" : "", aModCount, aModCount != 1 ? "s" : ""));
            brandings = brd.build();
            brandingsNoMC = brandings.subList(1, brandings.size());
        }
    }

    public static List<String> getBrandings(boolean includeMC) {
        if (brandings == null) {
            computeBranding();
        }
        return includeMC ? ImmutableList.copyOf(brandings) : ImmutableList.copyOf(brandingsNoMC);
    }

}