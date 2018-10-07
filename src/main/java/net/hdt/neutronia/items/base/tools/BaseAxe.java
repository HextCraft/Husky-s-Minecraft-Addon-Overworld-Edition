package net.hdt.neutronia.items.base.tools;

import net.hdt.huskylib2.item.ItemModAxe;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.items.INeutroniaItem;

public class BaseAxe extends ItemModAxe implements INeutroniaItem {

    public BaseAxe(String name, ToolMaterial material) {
        super(material.getAttackDamage(), 1.0f, material, name);
        setCreativeTab(Neutronia.CREATIVE_TAB);
    }
}
