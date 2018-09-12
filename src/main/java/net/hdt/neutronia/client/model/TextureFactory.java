package net.hdt.neutronia.client.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import net.hdt.neutronia.base.util.Reference;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

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
