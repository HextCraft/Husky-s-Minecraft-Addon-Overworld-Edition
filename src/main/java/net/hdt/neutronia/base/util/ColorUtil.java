package net.hdt.neutronia.base.util;

public class ColorUtil {
    public static int desaturate(int color, float amount) {
        java.awt.Color c = new java.awt.Color(color);
        float[] hsb = java.awt.Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
        hsb[1] = hsb[1] * amount;
        return java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
    }

    public static int desaturateAndBrighten(int color, float amount) {
        java.awt.Color c = new java.awt.Color(color);
        float[] hsb = java.awt.Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
        hsb[1] = hsb[1] * amount;
        hsb[2] = hsb[2] * 1.2F;
        return java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
    }
}
