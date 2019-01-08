package team.abnormal.neutronia.items;

import net.minecraft.creativetab.CreativeTabs;

public class ItemFood extends ItemModFood implements INeutroniaItem {

    public ItemFood(String name, CreativeTabs creativeTabs, int amount, boolean isWolfFood) {
        super(name, creativeTabs, amount, isWolfFood);
    }

    public ItemFood(String name, CreativeTabs creativeTabs, int amount, float saturation, boolean isWolfFood) {
        super(name, creativeTabs, amount, saturation, isWolfFood);
    }

    public ItemFood(String name, int amount, boolean isWolfFood) {
        super(name, CreativeTabs.FOOD, amount, isWolfFood);
    }

    public ItemFood(String name, int amount, float saturation, boolean isWolfFood) {
        super(name, CreativeTabs.FOOD, amount, saturation, isWolfFood);
    }

}
