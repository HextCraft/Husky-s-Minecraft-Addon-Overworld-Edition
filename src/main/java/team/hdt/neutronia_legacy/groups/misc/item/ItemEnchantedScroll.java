package team.hdt.neutronia_legacy.groups.misc.item;

import team.hdt.huskylib.item.ItemMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import team.hdt.neutronia_legacy.base.items.INeutroniaItem;
import team.hdt.neutronia_legacy.groups.misc.feature.EnchantedScrolls;

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
