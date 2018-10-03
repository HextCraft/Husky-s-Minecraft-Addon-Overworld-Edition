package net.hdt.neutronia.items;

import net.hdt.neutronia.base.util.ColorUtilsIdk;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemPhantomMembrane extends ItemBase {

    public ItemPhantomMembrane() {
        super("phantom_membrane", NCreativeTabs.NEUTRONIA_MAIN);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return ColorUtilsIdk.getClosest(0xd8cda9) + super.getItemStackDisplayName(stack);
    }

}
