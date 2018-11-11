package net.hdt.neutronia.base.client.gui.utils;

import net.hdt.neutronia.base.util.Localization;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.client.config.HoverChecker;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiTooltip {

    protected final ITooltipRenderer renderer;
    protected final HoverChecker checker;
    protected final List<String> tooltip;
    public GuiTooltip(@Nonnull final ITooltipRenderer renderer, @Nonnull GuiButton button,
                      @Nonnull final String tipText) {
        this(renderer, button, tipText, 200);
    }

    public GuiTooltip(@Nonnull final ITooltipRenderer renderer, @Nonnull GuiButton button,
                      @Nonnull final String tipText, final int width) {
        final FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        this.renderer = renderer;
        this.checker = new HoverChecker(button, 800);
        this.tooltip = generateTooltip(font, tipText, width);
    }

    private List<String> generateTooltip(@Nonnull FontRenderer font, @Nonnull final String langKey, final int width) {
        final String t = Localization.format(langKey);
        return font.listFormattedStringToWidth(t, width);
    }

    public boolean handle(final int mouseX, final int mouseY) {
        if (this.checker.checkHover(mouseX, mouseY)) {
            this.renderer.drawTooltip(mouseX, mouseY, this.tooltip);
            return true;
        }
        return false;
    }

    public static interface ITooltipRenderer {
        void drawTooltip(final int x, final int y, @Nonnull final List<String> text);
    }
}