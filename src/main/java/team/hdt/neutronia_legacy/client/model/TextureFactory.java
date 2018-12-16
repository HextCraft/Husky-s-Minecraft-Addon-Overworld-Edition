package team.hdt.neutronia_legacy.client.model;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import team.hdt.neutronia_legacy.base.util.Reference;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TextureFactory {

    private HashMap<Integer, Sprite> map = new HashMap<>();

    TextureAtlasSprite color(BufferedImage sprite, Color color) {
        int rgb = color.getRGB();
        if (map.containsKey(rgb)) {
            return map.get(rgb);
        }
        for (int x = 0; x < sprite.getWidth(); x++) {
            for (int y = 0; y < sprite.getWidth(); y++) {
                sprite.setRGB(x, y, rgb);
            }
        }
        return new Sprite(Reference.MOD_ID + ":color" + rgb, sprite);
    }

    public class Sprite extends TextureAtlasSprite {

        final BufferedImage image;

        protected Sprite(String name, BufferedImage image) {
            super(name);
            this.image = image;
        }


    }
}
