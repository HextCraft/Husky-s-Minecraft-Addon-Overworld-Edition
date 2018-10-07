package net.hdt.neutronia.items.base.tools;

import net.hdt.huskylib2.item.ItemModSword;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.items.INeutroniaItem;

public class BaseSword extends ItemModSword implements INeutroniaItem {

    public BaseSword(String name, ToolMaterial material) {
        super(name, material);
        setCreativeTab(Neutronia.CREATIVE_TAB);
    }

}
