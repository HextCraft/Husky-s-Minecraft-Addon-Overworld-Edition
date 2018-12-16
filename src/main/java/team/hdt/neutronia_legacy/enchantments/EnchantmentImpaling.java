package team.hdt.neutronia_legacy.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.util.EnumHelper;
import team.hdt.neutronia_legacy.items.ItemTrident;

public class EnchantmentImpaling extends Enchantment {

    private static final EnumEnchantmentType TRIDENT = EnumHelper.addEnchantmentType("trident", itemIn -> itemIn instanceof ItemTrident);

    public EnchantmentImpaling(Enchantment.Rarity p_i48786_1_, EntityEquipmentSlot... p_i48786_2_) {
        super(p_i48786_1_, TRIDENT, p_i48786_2_);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return 1 + (enchantmentLevel - 1) * 8;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 20;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 5;
    }

    /**
     * Calculates the additional damage that will be dealt by an item with this enchantment. This alternative to
     * calcModifierDamage is sensitive to the targets EnumCreatureAttribute.
     */
    @Override
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType) {
        return creatureType == EnumCreatureAttribute.UNDEAD ? (float) level * 2.5F : 0.0F;
    }
}
