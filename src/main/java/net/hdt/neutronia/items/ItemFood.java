package net.hdt.neutronia.items;

import net.hdt.neutronia.base.items.INeutroniaItem;
import net.minecraft.creativetab.CreativeTabs;

public class ItemFood extends ItemModFood implements INeutroniaItem {

    public ItemFood(String name, CreativeTabs creativeTabs, int amount, boolean isWolfFood) {
        super(name, creativeTabs, amount, isWolfFood);
    }

    public ItemFood(String name, CreativeTabs creativeTabs, int amount, float saturation, boolean isWolfFood) {
        super(name, creativeTabs, amount, saturation, isWolfFood);
    }

}
