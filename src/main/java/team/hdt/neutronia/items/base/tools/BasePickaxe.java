package team.hdt.neutronia.items.base.tools;

import team.hdt.huskylib.item.ItemModPickaxe;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia.base.items.INeutroniaItem;

public class BasePickaxe extends ItemModPickaxe implements INeutroniaItem {

    public BasePickaxe(String name, ToolMaterial material) {
        super(material, name);
        setCreativeTab(CreativeTabs.TOOLS);
    }

}
