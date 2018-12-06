package team.hdt.neutronia.items.base.tools;

import team.hdt.huskylib.item.ItemModAxe;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia.base.items.INeutroniaItem;

public class BaseAxe extends ItemModAxe implements INeutroniaItem {

    public BaseAxe(String name, ToolMaterial material) {
        super(material.getAttackDamage(), 1.0f, material, name);
        setCreativeTab(CreativeTabs.TOOLS);
    }
}
