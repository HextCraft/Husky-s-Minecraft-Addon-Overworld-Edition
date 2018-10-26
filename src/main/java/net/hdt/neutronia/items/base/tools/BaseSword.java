package net.hdt.neutronia.items.base.tools;

import net.hdt.huskylib2.item.ItemModSword;
import net.hdt.neutronia.base.items.INeutroniaItem;
import net.minecraft.creativetab.CreativeTabs;

public class BaseSword extends ItemModSword implements INeutroniaItem {

    public BaseSword(String name, ToolMaterial material) {
        super(name, material);
        setCreativeTab(CreativeTabs.COMBAT);
    }

}
