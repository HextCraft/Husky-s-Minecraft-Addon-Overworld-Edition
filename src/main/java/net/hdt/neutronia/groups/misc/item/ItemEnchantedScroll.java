package net.hdt.neutronia.groups.misc.item;

import net.hdt.huskylib2.item.ItemMod;
import net.hdt.neutronia.base.items.INeutroniaItem;
import net.hdt.neutronia.groups.misc.feature.EnchantedScrolls;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemEnchantedScroll extends ItemMod implements INeutroniaItem {

    public ItemEnchantedScroll() {
        super("enchanted_scroll");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (isInCreativeTab(tab))
            for (Enchantment e : EnchantedScrolls.validEnchants) {
                ItemStack stack = new ItemStack(this);
                stack.addEnchantment(e, e.getMaxLevel());
                subItems.add(stack);
            }
    }

}
