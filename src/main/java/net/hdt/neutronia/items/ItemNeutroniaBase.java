package net.hdt.neutronia.items;

import net.hdt.huskylib2.item.ItemMod;
import net.hdt.neutronia.base.items.INeutroniaItem;
import net.minecraft.creativetab.CreativeTabs;

public class ItemNeutroniaBase extends ItemMod implements INeutroniaItem {

    public ItemNeutroniaBase(String name, String... variants) {
        super(name, variants);
        setCreativeTab(CreativeTabs.MISC);
    }

}
