package net.hdt.neutronia.items.base.tools;

import net.hdt.huskylib2.item.ItemModSpade;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.items.INeutroniaItem;

public class BaseShovel extends ItemModSpade implements INeutroniaItem {

    public BaseShovel(String name, ToolMaterial material) {
        super(material, name);
        setCreativeTab(Neutronia.CREATIVE_TAB);
    }

}
