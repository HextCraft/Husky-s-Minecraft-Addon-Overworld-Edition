package team.hdt.neutronia.base.client.gui;

import com.google.common.base.Preconditions;
import net.minecraft.util.ResourceLocation;

public class Icon {

    public final ResourceLocation texture;

    public final float minU;
    public final float maxU;
    public final float minV;
    public final float maxV;

    public final int width;
    public final int height;

    public Icon(ResourceLocation texture, float minU, float maxU, float minV, float maxV, int width, int height) {
        Preconditions.checkNotNull(texture);
        this.texture = texture;

        this.minU = minU;
        this.maxU = maxU;
        this.minV = minV;
        this.maxV = maxV;

        this.width = width;
        this.height = height;
    }

    public static Icon createSheetIcon(ResourceLocation texture, int x, int y, int width, int height) {
        float minU = x / 256.0f;
        float minV = y / 256.0f;
        float maxU = (x + width) / 256.0f;
        float maxV = (y + height) / 256.0f;
        return new Icon(texture, minU, maxU, minV, maxV, Math.abs(width), Math.abs(height));
    }

    public float getInterpolatedU(double p) {
        return minU + (maxU - minU) * (float) p / 16.0f;
    }

    public float getInterpolatedV(double p) {
        return minV + (maxV - minV) * (float) p / 16.0f;
    }

    public Icon mirrorVertical() {
        return new Icon(texture, maxU, minU, minV, maxV, width, height);
    }

    public Icon mirrorHorizontal() {
        return new Icon(texture, minU, maxU, maxV, minV, width, height);
    }
}