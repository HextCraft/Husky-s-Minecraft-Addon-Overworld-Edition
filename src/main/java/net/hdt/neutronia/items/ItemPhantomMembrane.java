package net.hdt.neutronia.items;

import net.hdt.neutronia.base.util.ColorUtilsIdk;
import net.minecraft.item.ItemStack;

public class ItemPhantomMembrane extends ItemNeutroniaBase {

    public ItemPhantomMembrane() {
        super("phantom_membrane");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return ColorUtilsIdk.getClosest(0xd8cda9) + super.getItemStackDisplayName(stack);
    }

}
