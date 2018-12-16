package team.hdt.neutronia_legacy.items;

import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia_legacy.base.items.INeutroniaItem;

public class ItemFood extends ItemModFood implements INeutroniaItem {

    public ItemFood(String name, CreativeTabs creativeTabs, int amount, boolean isWolfFood) {
        super(name, creativeTabs, amount, isWolfFood);
    }

    public ItemFood(String name, CreativeTabs creativeTabs, int amount, float saturation, boolean isWolfFood) {
        super(name, creativeTabs, amount, saturation, isWolfFood);
    }

}
