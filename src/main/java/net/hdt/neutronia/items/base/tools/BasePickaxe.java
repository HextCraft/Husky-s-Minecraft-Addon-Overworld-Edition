package net.hdt.neutronia.items.base.tools;

import net.hdt.huskylib2.item.ItemModPickaxe;
import net.hdt.neutronia.base.items.INeutroniaItem;
import net.minecraft.creativetab.CreativeTabs;

public class BasePickaxe extends ItemModPickaxe implements INeutroniaItem {

    public BasePickaxe(String name, ToolMaterial material) {
        super(material, name);
        setCreativeTab(CreativeTabs.TOOLS);
    }

}
