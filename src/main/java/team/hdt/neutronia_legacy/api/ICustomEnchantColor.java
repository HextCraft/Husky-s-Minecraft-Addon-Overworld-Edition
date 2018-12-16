package team.hdt.neutronia_legacy.api;

import net.minecraft.item.ItemStack;

/**
 * Implement on an Item to allow it to have a custom glint color.
 */
public interface ICustomEnchantColor {

    public int getEnchantEffectColor(ItemStack stack);

    /**
     * Due to how enchantment color blending works, by default, the brightness of the effect
     * color is truncated so the sum of RGB is less or equal to 396, the sum of the RGB
     * components of the vanilla purple color. Setting this to false allows the color to go
     * as bright as possible, up to complete opaque if (255, 255, 255).
     */
    public default boolean shouldTruncateColorBrightness(ItemStack stack) {
        return true;
    }

}
