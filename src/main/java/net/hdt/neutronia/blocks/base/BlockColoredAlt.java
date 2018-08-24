package net.hdt.neutronia.blocks.base;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.huskylib2.interf.IBlockColorProvider;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.apache.commons.lang3.text.WordUtils;

import javax.annotation.Nullable;
import java.util.List;

public class BlockColoredAlt extends BlockMod implements IBlockColorProvider, INeutroniaBlock {

    public final EnumDyeColor color;

    public BlockColoredAlt(String name, EnumDyeColor color) {
        super(color.getName() + "_" + name, Material.ROCK);
        this.color = color;
    }

    public BlockColoredAlt(Material material, String name, EnumDyeColor color) {
        super(color.getName() + "_" + name, material);
        this.color = color;
    }

    private static TextFormatting getFromColor(EnumDyeColor color) {
        switch (color) {
            case ORANGE:
                return TextFormatting.GOLD;
            case MAGENTA:
                return TextFormatting.LIGHT_PURPLE;
            case LIGHT_BLUE:
                return TextFormatting.BLUE;
            case YELLOW:
                return TextFormatting.YELLOW;
            case LIME:
                return TextFormatting.GREEN;
            case PINK:
                return TextFormatting.LIGHT_PURPLE;
            case GRAY:
                return TextFormatting.DARK_GRAY;
            case SILVER:
                return TextFormatting.GRAY;
            case CYAN:
                return TextFormatting.DARK_AQUA;
            case PURPLE:
                return TextFormatting.DARK_PURPLE;
            case BLUE:
                return TextFormatting.DARK_BLUE;
            case BROWN:
                return TextFormatting.GOLD;
            case GREEN:
                return TextFormatting.DARK_GREEN;
            case RED:
                return TextFormatting.DARK_RED;
            case BLACK:
                return TextFormatting.BLACK;
            default:
                return TextFormatting.WHITE;
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        if (!GuiScreen.isShiftKeyDown()) {
            tooltip.add("Hold " + TextFormatting.BOLD + getFromColor(color) + "SHIFT " + TextFormatting.GRAY + "for more information");
        } else {
            String colorName = color.getName().replace("_", " ");
            colorName = WordUtils.capitalize(colorName);
            tooltip.add("Color: " + TextFormatting.BOLD.toString() + getFromColor(color).toString() + colorName);
        }
    }

    @Override
    public IBlockColor getBlockColor() {
        return (state, worldIn, pos, tintIndex) -> color.getColorValue();
    }

    @Override
    public IItemColor getItemColor() {
        return (stack, tintIndex) -> color.getColorValue();
    }

}