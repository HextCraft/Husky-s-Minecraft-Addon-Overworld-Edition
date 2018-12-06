package team.hdt.neutronia.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.util.EnumHelper;
import team.hdt.neutronia.items.ItemTrident;

public class EnchantmentChanneling extends Enchantment {

    private static final EnumEnchantmentType TRIDENT = EnumHelper.addEnchantmentType("trident", itemIn -> itemIn instanceof ItemTrident);

    public EnchantmentChanneling(Enchantment.Rarity p_i48787_1_, EntityEquipmentSlot... p_i48787_2_) {
        super(p_i48787_1_, TRIDENT, p_i48787_2_);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return 25;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }
}
