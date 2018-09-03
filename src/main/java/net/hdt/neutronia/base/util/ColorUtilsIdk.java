package net.hdt.neutronia.base.util;

import com.google.common.collect.Maps;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.text.TextFormatting;

import java.util.Map;

public class ColorUtilsIdk {
    private static final int[] TEXT_COLOURS = new int[16];
    private static final Map<EnumDyeColor, float[]> DYE_TO_RGB = Maps.newEnumMap(EnumDyeColor.class);

    static {
        for (int i = 0; i < 16; ++i) {
            int offset = (i >> 3 & 1) * 85;
            int red = (i >> 2 & 1) * 170 + offset;
            int green = (i >> 1 & 1) * 170 + offset;
            int blue = (i & 1) * 170 + offset;
            if (i == 6) {
                red += 85;
            }
            TEXT_COLOURS[i] = ColorUtilsIdk.fromRGB(red, green, blue);
        }

        DYE_TO_RGB.put(EnumDyeColor.WHITE, new float[]{1.0F, 1.0F, 1.0F});
        DYE_TO_RGB.put(EnumDyeColor.ORANGE, new float[]{0.85F, 0.5F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.MAGENTA, new float[]{0.7F, 0.3F, 0.85F});
        DYE_TO_RGB.put(EnumDyeColor.LIGHT_BLUE, new float[]{0.4F, 0.6F, 0.85F});
        DYE_TO_RGB.put(EnumDyeColor.YELLOW, new float[]{0.9F, 0.9F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.LIME, new float[]{0.5F, 0.8F, 0.1F});
        DYE_TO_RGB.put(EnumDyeColor.PINK, new float[]{0.95F, 0.5F, 0.65F});
        DYE_TO_RGB.put(EnumDyeColor.GRAY, new float[]{0.3F, 0.3F, 0.3F});
        DYE_TO_RGB.put(EnumDyeColor.SILVER, new float[]{0.6F, 0.6F, 0.6F});
        DYE_TO_RGB.put(EnumDyeColor.CYAN, new float[]{0.3F, 0.5F, 0.6F});
        DYE_TO_RGB.put(EnumDyeColor.PURPLE, new float[]{0.5F, 0.25F, 0.7F});
        DYE_TO_RGB.put(EnumDyeColor.BLUE, new float[]{0.2F, 0.3F, 0.7F});
        DYE_TO_RGB.put(EnumDyeColor.BROWN, new float[]{0.4F, 0.3F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.GREEN, new float[]{0.4F, 0.5F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.RED, new float[]{0.6F, 0.2F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.BLACK, new float[]{0.1F, 0.1F, 0.1F});
    }

    private static int fromRGB(int red, int green, int blue) {
        return (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
    }

    private static float[] toRGBFloatArray(int colour) {
        int red = (colour >> 16) & 0xFF;
        int green = (colour >> 8) & 0xFF;
        int blue = colour & 0xFF;
        return new float[]{red / 255.0F, green / 255.0F, blue / 255.0F};
    }

    private static float[] toHSVFloatArray(float[] colour) {
        return java.awt.Color.RGBtoHSB((int) (colour[0] * 255), (int) (colour[1] * 255), (int) (colour[2] * 255), null);
    }

    private static float[] toHSVFloatArray(int colour) {
        int red = (colour >> 16) & 0xFF;
        int green = (colour >> 8) & 0xFF;
        int blue = colour & 0xFF;
        return java.awt.Color.RGBtoHSB(red, green, blue, null);
    }

    public static TextFormatting getClosest(int colour) {
        return ColorUtilsIdk.getClosest(ColorUtilsIdk.toRGBFloatArray(colour));
    }

    private static TextFormatting getClosest(float[] colour) {
        colour = ColorUtilsIdk.toHSVFloatArray(colour);
        TextFormatting closest = TextFormatting.WHITE;
        float closestDelta = Float.MAX_VALUE;
        for (int i = 0; i < TEXT_COLOURS.length; i++) {
            float[] dyeColour = ColorUtilsIdk.toHSVFloatArray(ColorUtilsIdk.TEXT_COLOURS[i]);
            float deltaHue = Math.abs(dyeColour[0] - colour[0]);
            float deltaSaturation = Math.abs(dyeColour[1] - colour[1]);
            float deltaBrightness = Math.abs(dyeColour[2] - colour[2]);
            float delta = deltaHue + deltaSaturation + deltaBrightness;
            if (delta < closestDelta) {
                closestDelta = delta;
                closest = TextFormatting.fromColorIndex(i);
            }
        }
        return closest;
    }

}