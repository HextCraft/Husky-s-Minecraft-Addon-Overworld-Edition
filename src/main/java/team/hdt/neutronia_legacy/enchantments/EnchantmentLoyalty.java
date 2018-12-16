package team.hdt.neutronia_legacy.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.util.EnumHelper;
import team.hdt.neutronia_legacy.items.ItemTrident;

import java.util.Objects;

public class EnchantmentLoyalty extends Enchantment {

    private static final EnumEnchantmentType TRIDENT = EnumHelper.addEnchantmentType("trident", itemIn -> itemIn instanceof ItemTrident);

    public EnchantmentLoyalty(Enchantment.Rarity p_i48785_1_, EntityEquipmentSlot... p_i48785_2_) {
        super(p_i48785_1_, Objects.requireNonNull(TRIDENT), p_i48785_2_);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return 5 + enchantmentLevel * 7;
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
        return 3;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }
}
