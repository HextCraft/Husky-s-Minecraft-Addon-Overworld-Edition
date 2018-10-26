package net.hdt.neutronia.items.base.tools;

import net.hdt.huskylib2.item.ItemModAxe;
import net.hdt.neutronia.base.items.INeutroniaItem;
import net.minecraft.creativetab.CreativeTabs;

public class BaseAxe extends ItemModAxe implements INeutroniaItem {

    public BaseAxe(String name, ToolMaterial material) {
        super(material.getAttackDamage(), 1.0f, material, name);
        setCreativeTab(CreativeTabs.TOOLS);
    }
}
