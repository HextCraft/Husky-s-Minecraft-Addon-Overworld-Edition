package net.hdt.neutronia.items;

import net.hdt.huskylib2.item.ItemMod;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.items.INeutroniaItem;

public class ItemNeutroniaBase extends ItemMod implements INeutroniaItem {

    public ItemNeutroniaBase(String name, String... variants) {
        super(name, variants);
        setCreativeTab(Neutronia.CREATIVE_TAB);
    }

}
