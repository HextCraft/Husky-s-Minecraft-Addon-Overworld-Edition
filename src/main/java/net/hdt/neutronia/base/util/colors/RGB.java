package net.hdt.neutronia.base.util.colors;

public class RGB {
    public int r;
    public int g;
    public int b;

    public RGB(float r, float g, float b) {
        this.r = ((int) (r * 255)) & 0xFF;
        this.g = ((int) (g * 255)) & 0xFF;
        this.b = ((int) (b * 255)) & 0xFF;
    }

    public RGB(int r, int g, int b) {
        this.r = r & 0xFF;
        this.g = g & 0xFF;
        this.b = b & 0xFF;
    }

    public RGB(int color) {
        this(((color & 0xFF0000) >> 16), ((color & 0x00FF00) >> 8), (color & 0x0000FF));
    }

    public RGB() {
    }

    public int getColor() {
        return r << 16 | g << 8 | b;
    }

    public void setColor(int color) {
        r = (color & 0xFF0000) >> 16;
        g = (color & 0x00FF00) >> 8;
        b = color & 0x0000FF;
    }

    public float getR() {
        return r / 255f;
    }

    public float getG() {
        return g / 255f;
    }

    public float getB() {
        return b / 255f;
    }

    public RGB interpolate(RGB other, double amount) {
        int iPolR = (int) (r * (1D - amount) + other.r * amount);
        int iPolG = (int) (g * (1D - amount) + other.g * amount);
        int iPolB = (int) (b * (1D - amount) + other.b * amount);
        return new RGB(iPolR, iPolG, iPolB);
    }

    public int distance(RGB other) {
        // Formula taken from http://www.compuphase.com/cmetric.htm
        int meanR = (this.r + other.r) >>> 1;
        int dR = this.r - other.r;
        int dG = this.g - other.g;
        int dB = this.b - other.b;

        int dR2 = dR * dR;
        int dG2 = dG * dG;
        int dB2 = dB * dB;

        return Math.abs((((512 + meanR) * dR2) >> 8)
                + (dG2 << 2)
                + (((767 - meanR) * dB2) >> 8));
    }
}